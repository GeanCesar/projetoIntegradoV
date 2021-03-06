package com.example.projetointegrado;

import androidx.annotation.NonNull;

import com.example.projetointegrado.modelos.User;;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsuarioLogado {

    public static User usuarioLogado;
    public static String cargo;


    public static FirebaseAuth firebaseAuth;
    public static DatabaseReference databaseUsuario;

    public static User getUsuarioLogado(){

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Users");

        databaseUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()){
                    User usuario = usuarioSnapshot.getValue(User.class);

                    if(usuario.getEmail().equalsIgnoreCase(firebaseAuth.getCurrentUser().getEmail())){
                        UsuarioLogado.usuarioLogado.setNome(usuario.getNome());
                        UsuarioLogado.cargo = usuario.getCargo();

                        User u = new User();
                        u.setEmail(usuario.getEmail());
                        u.setNome(usuario.getNome());

                        if(u!=null)
                            usuarioLogado = u;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        while(usuarioLogado == null){}

        return usuarioLogado;

    }

}

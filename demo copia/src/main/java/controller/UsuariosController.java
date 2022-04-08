package controller;

import dao.UsuarioDAO;
import models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuariosController {
    private final UsuarioDAO usuariosDAO;
    public UsuariosController(UsuarioDAO usuarioDAO){
        this.usuariosDAO=usuarioDAO;
    }
    //GET all
    @GetMapping("")
    public List<Usuario> obtenerUsuarios(){
        return usuariosDAO.obtenerUsuarios();
    }
}

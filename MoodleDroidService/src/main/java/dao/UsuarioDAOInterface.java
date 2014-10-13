package dao;

import entities.Usuario;

/**
 *
 * @author Felipe
 * @date 08/09/2013
 * 
 */
//TODO estudar se realmente existe a necessidade desta interface na arquitetura
interface UsuarioDAOInterface {
    public Usuario verifyUsuario(Usuario usuario);
    public void updateUsuario(Usuario usuarioSession);
}

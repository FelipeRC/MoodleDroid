package dao;

import java.util.ArrayList;

import entities.Postagem;

/**
 *
 * @author Felipe
 * @date 08/09/2013
 * 
 */
//TODO estudar se realmente existe a necessidade desta interface na arquitetura
interface PostagemDAOInterface {
    public Postagem verifyPostagem(Postagem postagem);
    
    public ArrayList<Postagem> retornaUltimasNoticias();
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.CarnetCheque;
import models.CarteBancaire;
import models.TypeCarte;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 21650
 */
public interface IServiceS <T>{
    void ajouter(T t)throws SQLException;
    void modifier(T t,int id)throws SQLException;
    void modifier2(T t)throws SQLException;
    void supprimer(T t)throws SQLException;
    List<T> recuperer()throws SQLException;
    TypeCarte getByIdBonplan(int id);
    TypeCarte getCategorieById(int id) throws SQLException;
    CarteBancaire getByRefProduit(String id) throws SQLException;
    List<CarnetCheque> afficher() throws SQLException ;
    //void refuser(T t)throws SQLException ;
    //void accepter(T t)throws SQLException ;
}

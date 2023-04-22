package com.example.tripService.Repository;

import com.example.tripService.Domain.Excursie;
import com.example.tripService.Utils.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@org.springframework.stereotype.Repository
public class ExcursieDBRepo implements Repository<Integer, Excursie> {

    @Autowired
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    @Override
    public void adauga(Excursie excursie) {
        logger.traceEntry("saving excursie {} ",excursie);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into excursie(id_obiectiv, id_firma_transport, ora, nr_locuri_totale, pret) values (?,?,?,?,?)")){
            preStmt.setInt(1, excursie.getIdObiectiv());
            preStmt.setInt(2,excursie.getIdFirmaTransport());
            preStmt.setInt(3,excursie.getOra());
            preStmt.setInt(4, excursie.getNrLocuriTotale());
            preStmt.setFloat(5,excursie.getPret());
            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void sterge(Excursie excursie) {
        logger.traceEntry("deleting excursie {} ",excursie);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from excursie where id=?")){
            preStmt.setInt(1, excursie.getId());
            int result=preStmt.executeUpdate();
            logger.trace("Deleted {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Excursie cautaId(Integer id) {
        List<Excursie> excursieLst = getAll();
        for (Excursie e:excursieLst){
            if (e.getId().equals(id))
                return e;
        }
        return null;
    }

    @Override
    public List<Excursie> getAll() {
        logger.traceEntry("get all excursii");
        Connection con=dbUtils.getConnection();
        List<Excursie> excursieLst = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from excursie"))
        {
            try(ResultSet result = preStmt.executeQuery())
            {
                while(result.next())
                {
                    Integer id = result.getInt("id");
                    Integer idObiective = result.getInt("id_obiectiv");
                    Integer idFirmaTransport = result.getInt("id_firma_transport");
                    Integer ora = result.getInt("ora");
                    Integer nrLocuriTotale = result.getInt("nr_locuri_totale");
                    Float pret = result.getFloat("pret");

                    Excursie excursie = new Excursie(id,idObiective,idFirmaTransport,ora,pret,nrLocuriTotale);
                    excursieLst.add(excursie);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(excursieLst);
        return excursieLst;
    }

    @Override
    public void update(Excursie excursie, Excursie nouaExcursie) {
        logger.traceEntry("updating excursie {} into {}",excursie,nouaExcursie);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("update excursie set id_obiectiv=?, id_firma_transport=?, ora=?, nr_locuri_totale=?, pret=? where id=?")){
            preStmt.setInt(1, nouaExcursie.getIdObiectiv());
            preStmt.setInt(2,nouaExcursie.getIdFirmaTransport());
            preStmt.setInt(3,nouaExcursie.getOra());
            preStmt.setInt(4, nouaExcursie.getNrLocuriTotale());
            preStmt.setFloat(5,nouaExcursie.getPret());
            preStmt.setInt(6, excursie.getId());

            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("DB Error"+e);
        }
    }
}

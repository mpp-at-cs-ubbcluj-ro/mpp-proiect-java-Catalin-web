package com.example.tripService.Repository;

import com.example.tripService.Domain.FirmaTransport;
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
public class FirmaTransportDBRepo implements Repository<Integer, FirmaTransport> {
    @Autowired
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    @Override
    public void adauga(FirmaTransport firmaTransport) {
        logger.traceEntry("saving firma transport {} ",firmaTransport);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into firma_transport(nume) values (?)")){
            preStmt.setString(1, firmaTransport.getNume());

            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void sterge(FirmaTransport firmaTransport) {
        logger.traceEntry("deleting firma transport {} ",firmaTransport);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from firma_transport where id=?")){
            preStmt.setInt(1, firmaTransport.getId());
            int result=preStmt.executeUpdate();
            logger.trace("Deleted {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public FirmaTransport cautaId(Integer id) {
        List<FirmaTransport> firmaTransportLst = getAll();
        for (FirmaTransport ft:firmaTransportLst){
            if (ft.getId().equals(id))
                return ft;
        }
        return null;
    }

    @Override
    public List<FirmaTransport> getAll() {
        logger.traceEntry("get all firma transport");
        Connection con=dbUtils.getConnection();
        List<FirmaTransport> firmaTransportLst = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from firma_transport"))
        {
            try(ResultSet result = preStmt.executeQuery())
            {
                while(result.next())
                {
                    Integer id = result.getInt("id");
                    String nume = result.getString("nume");

                    FirmaTransport firmaTransport = new FirmaTransport(id,nume);
                    firmaTransportLst.add(firmaTransport);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(firmaTransportLst);
        return firmaTransportLst;
    }

    @Override
    public void update(FirmaTransport firmaTransport, FirmaTransport nouaFirmaTransport) {
        logger.traceEntry("updating firma transport {} into {}",firmaTransport,nouaFirmaTransport);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("update firma_transport set nume=? where id=?")){
            preStmt.setString(1, nouaFirmaTransport.getNume());
            preStmt.setInt(2,firmaTransport.getId());

            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("DB Error"+e);
        }
    }
}

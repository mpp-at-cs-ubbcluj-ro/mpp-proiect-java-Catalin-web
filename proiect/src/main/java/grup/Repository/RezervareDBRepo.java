package grup.Repository;

import grup.Domain.Rezervare;
import grup.Utils.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RezervareDBRepo implements Repository<Integer, Rezervare> {

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public RezervareDBRepo(Properties props){
        logger.info("Initializing rezervare DB with properties {}",props);
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public void adauga(Rezervare rezervare) {
        logger.traceEntry("saving rezervare {} ", rezervare);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into rezervare(id_excursie, id_persoana, nr_bilete) values (?,?,?)")){
            preStmt.setInt(1, rezervare.getIdExcursie());
            preStmt.setInt(2, rezervare.getIdPersoana());
            preStmt.setInt(3, rezervare.getNrBilete());

            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void sterge(Rezervare rezervare) {
        logger.traceEntry("deleting rezervare {} ",rezervare);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from rezervare where id=?")){
            preStmt.setInt(1, rezervare.getId());
            int result=preStmt.executeUpdate();
            logger.trace("Deleted {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Rezervare cautaId(Integer id) {
        List<Rezervare> rezervareLst = getAll();
        for (Rezervare r:rezervareLst){
            if (r.getId().equals(id))
                return r;
        }
        return null;
    }

    @Override
    public List<Rezervare> getAll() {
        logger.traceEntry("get all rezervare");
        Connection con=dbUtils.getConnection();
        List<Rezervare> rezervareLst = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from rezervare"))
        {
            try(ResultSet result = preStmt.executeQuery())
            {
                while(result.next())
                {
                    Integer id = result.getInt("id");
                    Integer idExcursie = result.getInt("id_excursie");
                    Integer idPersoana = result.getInt("id_persoana");
                    Integer nrBilete = result.getInt("nr_bilete");

                    Rezervare rezervare = new Rezervare(id,idExcursie,idPersoana,nrBilete);
                    rezervareLst.add(rezervare);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(rezervareLst);
        return rezervareLst;
    }

    @Override
    public void update(Rezervare rezervare, Rezervare nouaRezervare) {
        logger.traceEntry("updating rezervare {} into {}",rezervare,nouaRezervare);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("update rezervare set id_excursie=?, id_persoana=?, nr_bilete=? where id=?")){
            preStmt.setInt(1,nouaRezervare.getIdExcursie());
            preStmt.setInt(2,nouaRezervare.getIdPersoana());
            preStmt.setInt(3,nouaRezervare.getNrBilete());
            preStmt.setInt(4,rezervare.getId());

            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("DB Error"+e);
        }
    }
}

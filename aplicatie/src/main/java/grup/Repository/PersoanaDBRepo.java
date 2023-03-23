package grup.Repository;

import grup.Domain.Persoana;
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

public class PersoanaDBRepo implements Repository<Integer, Persoana> {

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public PersoanaDBRepo(Properties props){
        logger.info("Initializing firma prsoana DB with properties {}",props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void adauga(Persoana persoana) {
        logger.traceEntry("saving persoana {} ",persoana);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into persoana(nume, numar_telefon) values (?,?)")){
            preStmt.setString(1, persoana.getNume());
            preStmt.setString(2, persoana.getNumarTelefon());

            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void sterge(Persoana persoana) {
        logger.traceEntry("deleting persoana {} ",persoana);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from persoana where id=?")){
            preStmt.setInt(1, persoana.getId());
            int result=preStmt.executeUpdate();
            logger.trace("Deleted {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Persoana cautaId(Integer id) {
        List<Persoana> persoanaLst = getAll();
        for (Persoana p:persoanaLst){
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }

    @Override
    public List<Persoana> getAll() {
        logger.traceEntry("get all persoana");
        Connection con=dbUtils.getConnection();
        List<Persoana> persoanaLst = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from persoana"))
        {
            try(ResultSet result = preStmt.executeQuery())
            {
                while(result.next())
                {
                    Integer id = result.getInt("id");
                    String nume = result.getString("nume");
                    String numarTelefon = result.getString("numar_telefon");

                    Persoana persoana = new Persoana(id,nume,numarTelefon);
                    persoanaLst.add(persoana);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(persoanaLst);
        return persoanaLst;
    }

    @Override
    public void update(Persoana persoana, Persoana nouaPersoana) {
        logger.traceEntry("updating persoana {} into {}",persoana,nouaPersoana);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("update persoana set nume=?, numar_telefon=? where id=?")){
            preStmt.setString(1, nouaPersoana.getNume());
            preStmt.setString(2, nouaPersoana.getNumarTelefon());
            preStmt.setInt(3,persoana.getId());

            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("DB Error"+e);
        }
    }
}

package grup.Repository;

import grup.Domain.ObiectivTuristic;
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

public class ObiectivTuristicDBRepo implements Repository<Integer, ObiectivTuristic> {

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ObiectivTuristicDBRepo(Properties props){
        logger.info("Initializing firma obiectiv turistic DB with properties {}",props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void adauga(ObiectivTuristic obiectivTuristic) {
        logger.traceEntry("saving obiectiv turistic {} ",obiectivTuristic);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into obiectiv_turistic(nume) values (?)")){
            preStmt.setString(1, obiectivTuristic.getNume());

            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void sterge(ObiectivTuristic obiectivTuristic) {
        logger.traceEntry("deleting obiectiv turistic {} ",obiectivTuristic);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from obiectiv_turistic where id=?")){
            preStmt.setInt(1, obiectivTuristic.getId());
            int result=preStmt.executeUpdate();
            logger.trace("Deleted {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public ObiectivTuristic cautaId(Integer id) {
        List<ObiectivTuristic> obiectivTuristicLst = getAll();
        for (ObiectivTuristic o:obiectivTuristicLst){
            if (o.getId().equals(id))
                return o;
        }
        return null;
    }

    @Override
    public List<ObiectivTuristic> getAll() {
        logger.traceEntry("get all obiectiv turistic");
        Connection con=dbUtils.getConnection();
        List<ObiectivTuristic> obiectivTuristicLst = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from obiectiv_turistic"))
        {
            try(ResultSet result = preStmt.executeQuery())
            {
                while(result.next())
                {
                    Integer id = result.getInt("id");
                    String nume = result.getString("nume");

                    ObiectivTuristic obiectivTuristic = new ObiectivTuristic(id,nume);
                    obiectivTuristicLst.add(obiectivTuristic);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(obiectivTuristicLst);
        return obiectivTuristicLst;
    }

    @Override
    public void update(ObiectivTuristic obiectivTuristic, ObiectivTuristic nouObiectivTuristic) {
        logger.traceEntry("updating obiectiv turistic {} into {}",obiectivTuristic,nouObiectivTuristic);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("update obiectiv_turistic set nume=? where id=?")){
            preStmt.setString(1, nouObiectivTuristic.getNume());
            preStmt.setInt(2,obiectivTuristic.getId());

            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("DB Error"+e);
        }
    }
}

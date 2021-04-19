package atl.stibride.jdbc;

import atl.stibride.config.ConfigManager;
import atl.stibride.dto.StationDto;
import atl.stibride.dto.StopDto;
import atl.stibride.repository.RepositoryException;

import java.io.IOException;
import java.util.List;

public class Tests {
    public static void main(String[] args) {
        try {
            ConfigManager.getInstance().load();
            //
            //
            StationsDao stationsDao = new StationsDao();

            List<StationDto> list = stationsDao.selectAll();
            for (StationDto stationDto : list) {
                System.out.println("\t" + stationDto.getKey()
                        + " " + stationDto.getName()
                        + " " + stationDto.getLines());
            }

            StationDto station = stationsDao.select(8062);
            System.out.println("\t" + station.getKey() + " " + station.getName());

            String name = stationsDao.getStationName(8062);
            System.out.println(name);
            //
            //
            StopsDao stopsDao = new StopsDao();

            List<StopDto> l = stopsDao.selectAll();
            for (StopDto stopDto : l) {
                System.out.println("\t" + stopDto.getKey()
                        + " " + stopDto.getId_station()
                        + " " + stopDto.getId_order());
            }

        } catch (RepositoryException | IOException re) {
            System.out.println(re.getMessage());
        }

    }
}

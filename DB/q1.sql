select station_id as a_station
from (select station_id
      from line_detail
      where line_id = 1) as sub1
    except (select station_id
            from line_detail
            where line_id = 2)
order by a_station;
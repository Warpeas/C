select
  district,
  count(ld.station_id) as number,
  rank() over (
    order by
      count(ld.station_id)
  ) as rank
from stations
join line_detail ld on stations.station_id = ld.station_id
where
  line_id = 1
group by
  district;
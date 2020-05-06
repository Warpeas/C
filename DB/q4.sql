select *,
       rank() over (partition by line_id order by cnt desc) as rank
from (select line_id,
             l.station_id,
             count(l.station_id) as cnt
      from line_detail as l
               left join
           (select station_id
            from bus_lines) as sub on l.station_id = sub.station_id
      group by line_id, l.station_id) as sub1
where cnt >= 10
order by line_id, cnt, station_id desc
limit 10
offset
15;
select district, count(distinct line_id) as number, rank()over (order by count(distinct line_id) desc )
from line_detail
     join stations s on line_detail.station_id = s.station_id and district is not null and district<>''
group by district;
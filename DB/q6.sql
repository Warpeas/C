with lyf as (select distinct movieid
             from credits
                      join people p on credits.peopleid = p.peopleid
             where p.first_name = 'Yifei'
               and p.surname = 'Liu'
               and credited_as = 'A'
             group by movieid)
select count(distinct peopleid) count
from lyf
         join credits as c on lyf.movieid = c.movieid
where peopleid <> (select peopleid
                   from people
                   where first_name = 'Yifei'
                     and surname = 'Liu')
  and credited_as = 'A';
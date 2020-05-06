select district, chr, cnt
from (select substr(chinese_name, 1, 1)                 as chr,
             district,
             count(*)                                   as cnt,
             max(count(*)) over (partition by district) as max
      from stations
      where district is not null
        and district <> ''
      group by chr, district
     ) as sub
where cnt = max
order by district, convert_to(chr, 'GBK');
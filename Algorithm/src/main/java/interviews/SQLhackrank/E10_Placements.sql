

Select s.name from
    (Select * from Students) s
        inner join
    (Select * from friends) f
        inner join
    (Select * from Packages) p
        inner join
    (Select * from Packages) p1
    on s.id=f.id and p.id=s.id
        and p1.id=f.friend_id
        and p1.salary>p.salary order by p1.salary;
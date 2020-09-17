#关于join的一些操作
select * from company,country where company.id = country.id;

select * from company inner join country on company.country_id = country.id;

select * from company left join country on company.country_id = country.id;

select * from company right join country on company.country_id = country.id;

#mysql 不支持full join
#select * from company full join country on company.country_id = country.id;

#查询 “01”课程比“02”课程成绩高的学生的信息及课程分数
select *
from Student
         RIGHT JOIN (
    select t1.SId, class1, class2
    from (select SId, score as class1 from SC where SC.CId = '01') as t1,
         (select SId, score as class2 from SC where SC.CId = '02') as t2
    where t1.SId = t2.SId
      AND t1.class1 > t2.class2
) r
                    on Student.SId = r.SId;

select *
from Student
         RIGHT JOIN (
    select t1.SId, t1.score, t2.score as score2
    from (select SId, score from SC where SC.CId = '01') as t1,
         (select SId, score from SC where SC.CId = '02') as t2
    where t1.SId = t2.SId
      and t2.score > t1.score) t
                    on Student.SId = t.SId;

#查询SC表中同时存在 01 和 02 课程的情况
select *
from (select * from SC where CId = 01) as t1,
     (select * from SC where CId = 02) as t2
where t1.SId = t2.SId;

#查询SC表存在01课程但可能不存在02课程的情况（不存在时显示null)
select t1.SId, t1.CId, t1.score, t2.CId, t2.score
from (select * from SC where CId = 01) as t1
         left join
         (select * from SC where CId = 02) as t2
         on t1.SId = t2.SId;

#查询平均成绩大于等于60分的同学的学生编号，和学生姓名，和平均成绩
select *
from Student
         join(
    select SId, AVG(score) as avg
    from SC
    group by SId
    HAVING AVG(score) > 60) t
             on Student.SId = t.SId;

#查询SC表存在成绩的学生信息
select DISTINCT Student.*
from Student,
     SC
where Student.SId = SC.SId;

#查询所有同学的学生编号、姓名、选课总数、所有课程的的成绩总和
#先查询，后汇总
select t.Sname, t.Sage, count(1), sum(score)
from (select Student.SId, Sname, Sage, Ssex, SC.score, SC.CId
      from Student,
           SC
      where Student.SId = SC.SId) t
group by t.SId, t.Sname, t.Sage;
#先汇总，后与Student表结合
select *
from Student,
     (select SC.sid, sum(SC.score) as scoresum, count(SC.cid) as coursenumber from SC group by SC.sid) t
where Student.SId = t.SId;

#查有成绩的学生信息
#A为果，B为因
#结论 IN()适合B表比A表数据小的情况
select *
from Student
where SId in (select SId from SC);
#结论 exists 适合B表比A表数据大的情况
select *
from Student
where exists(select SId from SC where Student.SId = SC.SId);

#查询李姓老师的数量
select count(1)
from Teacher
where Tname like '李%';

#查询学过 张三 老师授课的同学的信息
#多表联合查询
select Student.*
from Student,
     Teacher,
     Course,
     SC
where Student.SId = SC.SId
  and SC.CId = Course.CId
  and Course.TId = Teacher.TId
  and Teacher.Tname = '张三';

#查询没有学完所有课程的同学的信息     学会利用having
select *
from Student
where SId in (
    select SId
    from SC
    group by SId
    having count(*) != (select count(1) from Course));

#8.查询至少有一门课与学号为 01 的同学所学相同的同学的信息
select *
from Student
where SId in (select distinct(SId)
              from SC
              where CId in (select CId from SC where SId = '01')
                and SId != '01');

#9.查询和 01 号的同学学习的课程完全相同的其他同学的信息
select SId
from SC
where SId <> '01'
group by sid
having group_concat(CId order by CId) = (select group_concat(CId order by CId) from SC where sid = '01');

#10.查询没学过张三老师讲授的任一门课程的学生姓名
select Sname
from Student
where SId not in (
    select SId
    from SC
    where CId in (
        select CId
        from Course,
             Teacher
        where Course.TId = Teacher.TId
          and Teacher.Tname = '张三'));

select *
from Student
where Student.sid not in (
    select SC.sid
    from SC,
         Course,
         Teacher
    where SC.cid = Course.cid
      and Course.tid = Teacher.tid
      and Teacher.tname = '张三'
);

#11.查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
select Student.SId, Student.Sname, avgscore
from Student
         join
     (select SId, avg(score) as avgscore
      from SC
      where SC.SId in
            (select SID from SC where score < 60 group by SId having count(1) > 1)
      group by SC.SId)
     on SId = SId;

#检索" 01 "课程分数小于 60，按分数降序排列的学生信息
select Student.*, SC.score
from Student,
     SC
where Student.SId = SC.SId
  and CId = '01'
  and score < 60
order by score desc;

#13 按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
select *
from SC
         join
         (select SId, AVG(score) as avgscore from SC group by SId) t
         on SC.SId = t.SId
order by avgscore desc;

#14 查询各科成绩最高分、最低分、和平均分
select CId,
       max(score)                                                            as 最高分,
       min(score)                                                               最低分,
       avg(score)                                                               平均分,
       count(1)                                                              as 选修人数,
       sum(case when score > 60 then 1 else 0 end) / count(1)                as 及格率,
       sum(case when score > 70 and score < 80 then 1 else 0 end) / count(1) as 中等率
from SC
group by CId;

#15-按各科成绩进行排序，并显示排名， Score 重复时保留名次空缺


#16-查询学生的总成绩，并进行排名，总分重复时不保留名次空缺
set @crank = 0;
select @crank := @crank + 1 as ranke, t.SId, t.total
from (select SId, sum(score) as total
      from SC
      group by SId
      order by total desc) t;

#mysql 8.0后有了rank,dense_rank,row_number 三个函数可供试用
select SId,
       sum(score)                              as total,
       rank() over (order by sum(score))       as r,
       dense_rank() over (order by sum(score)) as dense_r,
       row_number() over (order by sum(score)) as row_r
from SC
group by SId;

#17 统计各科成绩各分数段人数：
#课程编号，课程名称，[100-85]，[85-70]，[70-60]，[60-0] 及所占百分比
#列名包含特殊字符的需要用反引号来处理，1左边的键
select Course.Cname, `[100-85]`, `[85-70]`
from Course
         join (
    select CId,
           sum(case when score < 100 and score > 85 then 1 else 0 end) as "[100-85]",
           sum(case when score < 85 and score > 70 then 1 else 0 end)  as "[85-70]"
    from SC
    group by CId) t
     
select count(*) from SC group by CId;

#20-查询出只选修两门课程的学生学号和姓名
select *
from Student,(select SId from SC group by SId having COUNT(*) =2)t
where Student.SId = t.SId;

#21-查询男生、女生人数
select Ssex,count(*)
from Student group by Ssex;

#22-查询名字中含有风字的学生信息
select * from Student where Sname like '%风%'

#23-查询同名学生名单，统计同名人数
select Sname,count(*) from Student group by Sname having count(*) > 1;

#24-查询1990年出生的学生名单
select * from Student where YEAR(Sage) = 1990;

#25-成绩有重复的情况下，查询选修张三老师所授课程的学生中，成绩最高的学生信息及成绩
select * from SC,Student,Course,Teacher
where Tname = '张三'
  and Course.TId = Teacher.TId
  and Course.CId = SC.CId
  and Student.SId = SC.SId
  and score = (select max(SC.score) from SC,Course,Teacher where Tname = '张三'
                                              and Course.TId = Teacher.TId
                                              and Course.CId = SC.CId)

#
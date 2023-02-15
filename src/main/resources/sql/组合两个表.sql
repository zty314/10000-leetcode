<!--简单的关联查询-->
<!--https://leetcode.cn/problems/combine-two-tables/description/-->
select p.firstName,p.lastName,a.city,a.state from Person p left join Address a on p.personId = a.personId



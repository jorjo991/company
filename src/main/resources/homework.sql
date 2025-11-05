INSERT INTO public.adresses (city, steet, "number", office_id)
VALUES 
    ('Tbilisi', 'Rustaveli Ave', '12A', 1),
    ('Batumi', 'Chavchavadze St', '45', 2),
    ('Kutaisi', 'Tsereteli St', '8B', 3),
    ('Gori', 'Stalin Ave', '33', 4),
    ('Zugdidi', 'Rustaveli St', '19', 5),
    ('Telavi', 'Erekle II St', '7', 6),
    ('Poti', 'Agmashenebeli St', '21', 7),
    ('Marneuli', 'Akhmeteli St', '2', 8),
    ('Khashuri', 'Kostava St', '14A', 9),
    ('Ozurgeti', 'Gamsakhurdia St', '3', 10);  
delete from adress;

INSERT INTO public.companies (name)
VALUES
    ('TechVision'),
    ('GeoSoft'),
    ('DataBridge'),
    ('BluePeak'),
    ('InnovaLab'),
    ('SkyLink'),
    ('NextGen Solutions'),
    ('BrightCode'),
    ('AlphaWare'),
    ('CodeNest'); 

	
select * from companies; 
delete  from companies;
--Clients data

INSERT INTO public.clients (name, surname, email, birthday, active, company_id)
VALUES
    ('Nika', 'Kapanadze', 'nika.kapanadze@techvision.com', '1998-04-15', TRUE, 2),
    ('Lika', 'Beridze', 'lika.beridze@geosoft.com', '1995-07-22', TRUE, 3),
    ('Giorgi', 'Maisuradze', 'giorgi.maisuradze@databridge.com', '2000-03-11', TRUE, 4),
    ('Ana', 'Javakhishvili', 'ana.javakhishvili@bluepeak.com', '1992-10-05', FALSE, 5),
    ('Sandro', 'Kiknadze', 'sandro.kiknadze@innovalab.com', '1999-12-01', TRUE, 5),
    ('Mariam', 'Chkonia', 'mariam.chkonia@skylink.com', '1997-01-18', TRUE, 7),
    ('Dato', 'Khutsishvili', 'dato.khutsishvili@nextgensolutions.com', '1990-09-27', FALSE, 8),
    ('Elene', 'Giorgadze', 'elene.giorgadze@brightcode.com', '1996-05-14', TRUE, 9),
    ('Irakli', 'Lomidze', 'irakli.lomidze@alphaware.com', '1994-02-07', TRUE, 1),
    ('Tamar', 'Gventsadze', 'tamar.gventsadze@codenest.com', '1993-11-30', FALSE, 1); 

select * from clients;
delete * from clients where id=1;
	
--Budget data 

INSERT INTO public.budgets (total_amount, spend_amount, description, company_id)
VALUES
    (100000.00, 45000.00, 'Annual tech upgrade and R&D', 1),
    (85000.00, 52000.00, 'Software development and training', 2),
    (120000.00, 75000.00, 'Cloud infrastructure and analytics', 3),
    (90000.00, 30000.00, 'Marketing and outreach programs', 5),
    (110000.00, 60000.00, 'Product innovation and staffing', 6),
    (95000.00, 42000.00, 'Network maintenance and licensing', 7),
    (105000.00, 70000.00, 'AI research and prototype building', 8),
    (87000.00, 35000.00, 'Client engagement and partnerships', 9),
    (115000.00, 80000.00, 'Software deployment and testing', 10),
    (98000.00, 40000.00, 'New office setup and expansion', 4);
	
ALTER TABLE budgets
ADD COLUMN id BIGSERIAL;  

ALTER TABLE budgets
ADD CONSTRAINT budgets_pkey PRIMARY KEY (id);

delete from budgets;	

select * from budgets; 


--Department data  

INSERT INTO public.departments (name, "number", company_id)
VALUES
    ('Software Development', 101, 1),
    ('Data Science', 102, 2),
    ('Cloud Operations', 103, 3),
    ('Marketing', 104, 4),
    ('Research & Innovation', 105, 5),
    ('Network Engineering', 106, 6),
    ('AI Development', 107, 7),
    ('Customer Success', 108, 8),
    ('Quality Assurance', 109, 9),
    ('Human Resources', 110, 10),
    ('Product Management', 111, 1),
    ('UI/UX Design', 112, 2),
    ('Sales', 113, 3),
    ('Legal', 114, 4),
    ('Finance', 115, 5),
    ('Technical Support', 116, 6),
    ('Operations', 117, 7),
    ('Security', 118, 8),
    ('Procurement', 119, 9),
    ('Logistics', 120, 10);
	
ALTER SEQUENCE departments_id_seq RESTART WITH 1;



delete from departments;

select * from departments; 


--Emoloyees data
INSERT INTO public.employees (age, name, surname, email, birth_day, department_id)
VALUES
    (28, 'Nika', 'Kapanadze', 'nika.kapanadze@example.com', '1995-04-15', 1),
    (32, 'Lika', 'Beridze', 'lika.beridze@example.com', '1991-07-22', 2),
    (26, 'Giorgi', 'Maisuradze', 'giorgi.maisuradze@example.com', '1997-03-11', 3),
    (40, 'Ana', 'Javakhishvili', 'ana.javakhishvili@example.com', '1983-10-05', 4),
    (30, 'Sandro', 'Kiknadze', 'sandro.kiknadze@example.com', '1993-12-01', 5),
    (29, 'Mariam', 'Chkonia', 'mariam.chkonia@example.com', '1994-01-18', 6),
    (35, 'Dato', 'Khutsishvili', 'dato.khutsishvili@example.com', '1988-09-27', 7),
    (27, 'Elene', 'Giorgadze', 'elene.giorgadze@example.com', '1996-05-14', 8),
    (31, 'Irakli', 'Lomidze', 'irakli.lomidze@example.com', '1992-02-07', 9),
    (33, 'Tamar', 'Gventsadze', 'tamar.gventsadze@example.com', '1990-11-30', 10),
    (28, 'Levan', 'Mamaladze', 'levan.mamaladze@example.com', '1995-08-12', 11),
    (34, 'Nino', 'Tsiklauri', 'nino.tsiklauri@example.com', '1989-06-22', 12),
    (29, 'Goga', 'Bakradze', 'goga.bakradze@example.com', '1994-03-19', 13),
    (36, 'Ketevan', 'Lortkipanidze', 'ketevan.lortkipanidze@example.com', '1987-01-03', 14),
    (27, 'Vakhtang', 'Chkheidze', 'vakhtang.chkheidze@example.com', '1996-09-09', 15),
    (30, 'Salome', 'Berulava', 'salome.berulava@example.com', '1993-12-15', 16),
    (25, 'Zurab', 'Tsuladze', 'zurab.tsuladze@example.com', '1998-05-21', 17),
    (31, 'Tamar', 'Mchedlishvili', 'tamar.mchedlishvili@example.com', '1992-04-10', 18),
    (38, 'Leila', 'Gurgenidze', 'leila.gurgenidze@example.com', '1985-07-18', 19),
    (32, 'David', 'Shanidze', 'david.shanidze@example.com', '1991-11-01', 20),
    (29, 'Irma', 'Chikhradze', 'irma.chikhradze@example.com', '1994-02-28', 1),
    (33, 'Beka', 'Gogoladze', 'beka.gogoladze@example.com', '1990-06-07', 2),
    (27, 'Natia', 'Kvaratskhelia', 'natia.kvaratskhelia@example.com', '1996-03-23', 3),
    (35, 'Lasha', 'Bakhtadze', 'lasha.bakhtadze@example.com', '1988-10-11', 4),
    (28, 'Salome', 'Gelashvili', 'salome.gelashvili@example.com', '1995-12-19', 5),
    (30, 'Giorgi', 'Tsereteli', 'giorgi.tsereteli@example.com', '1993-01-07', 6),
    (26, 'Nana', 'Iashvili', 'nana.iashvili@example.com', '1997-09-03', 7),
    (34, 'Mikheil', 'Khomeriki', 'mikheil.khomeriki@example.com', '1989-05-15', 8),
    (29, 'Tamar', 'Pirtskhalava', 'tamar.pirtskhalava@example.com', '1994-07-29', 9),
    (31, 'Levan', 'Gogichaishvili', 'levan.gogichaishvili@example.com', '1992-08-21', 10); 

delete  from employees;
select * from employees;
ALTER SEQUENCE employees_id_seq RESTART WITH 1;


--Laptops data

INSERT INTO public.laptops (name, brand, color, employee_id)
VALUES
    ('ThinkPad X1 Carbon', 'Lenovo', 'Black', 1),
    ('MacBook Pro 16', 'Apple', 'Silver', 2),
    ('Dell XPS 13', 'Dell', 'White', 3),
    ('Surface Laptop 5', 'Microsoft', 'Platinum', 4),
    ('ZenBook 14', 'Asus', 'Blue', 5),
    ('EliteBook 840', 'HP', 'Silver', 6),
    ('MacBook Air M2', 'Apple', 'Space Gray', 7),
    ('Latitude 7420', 'Dell', 'Black', 8),
    ('ThinkPad T14', 'Lenovo', 'Black', 9),
    ('Surface Pro 9', 'Microsoft', 'Black', 10),
    ('ROG Strix G15', 'Asus', 'Black', 11),
    ('Pavilion 15', 'HP', 'Blue', 12),
    ('MacBook Pro 14', 'Apple', 'Silver', 13),
    ('XPS 15', 'Dell', 'Silver', 14),
    ('ThinkPad X13', 'Lenovo', 'Gray', 15),
    ('Surface Laptop Studio', 'Microsoft', 'Platinum', 16),
    ('ZenBook Flip 14', 'Asus', 'Gold', 17),
    ('Elite Dragonfly', 'HP', 'Blue', 18),
    ('MacBook Air 13', 'Apple', 'Silver', 19),
    ('Latitude 9520', 'Dell', 'Black', 20);

select* from laptops;	

delete from laptops; 
ALTER SEQUENCE laptops_id_seq RESTART WITH 1;
	

--Project data
INSERT INTO public.projects (name, finished, department_id)
VALUES
    ('Website Redesign', FALSE, 1),
    ('AI Model Development', TRUE, 2),
    ('Cloud Migration', FALSE, 3),
    ('Marketing Campaign Q1', TRUE, 4),
    ('Product Prototype', FALSE, 5),
    ('Network Upgrade', TRUE, 6),
    ('Chatbot Implementation', FALSE, 7),
    ('Customer Feedback System', TRUE, 8),
    ('Software QA Automation', FALSE, 9),
    ('HR Onboarding Portal', TRUE, 10),
    ('Mobile App Launch', FALSE, 11),
    ('Data Analytics Dashboard', TRUE, 12),
    ('CRM Integration', FALSE, 13),
    ('Legal Compliance Review', TRUE, 14),
    ('Financial Forecasting Tool', FALSE, 15),
    ('Tech Support Knowledge Base', TRUE, 16),
    ('Operations Optimization', FALSE, 17),
    ('Security Audit', TRUE, 18),
    ('Procurement System Upgrade', FALSE, 19),
    ('Logistics Tracking App', TRUE, 20); 

delete from projects;

select* from projects; 

ALTER SEQUENCE projects_id_seq RESTART WITH 1; 

INSERT INTO public.offices (name, capacity, company_id)
VALUES
    ('InnovaLab Office', 160, 6),
    ('SkyLink Center', 140, 7),
    ('NextGen Base', 130, 8),
    ('BrightCode Hall', 170, 9),
    ('AlphaWare Complex', 190, 10);

select * from offices;
	
ALTER SEQUENCE offices_id_seq RESTART WITH 1; 

-- rooms data 
INSERT INTO public.rooms (capacity, available, room_number, office_id)
VALUES
    (10, TRUE, 'R-101', 1),
    (8, FALSE, 'R-102', 1),
    (12, TRUE, 'R-201', 2),
    (6, TRUE, 'R-202', 2),
    (15, FALSE, 'R-301', 3),
    (20, TRUE, 'R-302', 3),
    (5, TRUE, 'R-401', 4),
    (9, FALSE, 'R-402', 4),
    (25, TRUE, 'R-501', 5),
    (18, TRUE, 'R-502', 5);


delete from rooms;
select * from rooms;
ALTER SEQUENCE rooms_id_seq RESTART WITH 1;   



INSERT INTO public.salary (amount, bonus, cut_percentage, employee_id)
VALUES
    (2500.00, 200.00, 5, 1),
    (2700.00, 250.00, 4, 2),
    (2600.00, 180.00, 6, 3),
    (2800.00, 220.00, 3, 4),
    (3000.00, 300.00, 5, 5),
    (3200.00, 350.00, 2, 6),
    (3100.00, 270.00, 4, 7),
    (2950.00, 240.00, 5, 8),
    (3300.00, 310.00, 3, 9),
    (3400.00, 320.00, 2, 10),
    (2550.00, 150.00, 5, 11),
    (2650.00, 180.00, 4, 12),
    (2750.00, 200.00, 3, 13),
    (2850.00, 220.00, 4, 14),
    (2950.00, 250.00, 5, 15),
    (3050.00, 260.00, 3, 16),
    (3150.00, 270.00, 4, 17),
    (3250.00, 300.00, 2, 18),
    (3350.00, 320.00, 3, 19),
    (3450.00, 330.00, 4, 20),
    (3550.00, 340.00, 2, 21),
    (3650.00, 350.00, 3, 22),
    (3750.00, 360.00, 4, 23),
    (3850.00, 370.00, 2, 24),
    (3950.00, 380.00, 3, 25),
    (4050.00, 390.00, 2, 26),
    (4150.00, 400.00, 3, 27),
    (4250.00, 420.00, 2, 28),
    (4350.00, 430.00, 3, 29),
    (4450.00, 440.00, 2, 30);



ALTER SEQUENCE salary_id_seq RESTART 1;

select * from salary;


-- task data 


INSERT INTO public.tasks (name, start_time, end_time, project_id)
VALUES
    ('Design Database Schema', '2025-01-05', '2025-01-10', 1),
    ('Implement Authentication Module', '2025-01-12', '2025-01-20', 1),
    ('UI Prototype Development', '2025-02-01', '2025-02-08', 2),
    ('API Integration', '2025-02-10', '2025-02-18', 2),
    ('Testing Core Features', '2025-03-01', '2025-03-05', 3),
    ('Bug Fixing Sprint 1', '2025-03-07', '2025-03-12', 3),
    ('Deploy to Staging', '2025-03-15', '2025-03-17', 4),
    ('Client Feedback Review', '2025-03-20', '2025-03-22', 4),
    ('Performance Optimization', '2025-04-01', '2025-04-10', 5),
    ('Documentation Draft', '2025-04-12', '2025-04-15', 5),
    ('Frontend Enhancement', '2025-04-20', '2025-04-28', 6),
    ('Security Testing', '2025-05-01', '2025-05-06', 6),
    ('Release Version 1.0', '2025-05-10', '2025-05-12', 7),
    ('Post-Release Support', '2025-05-15', '2025-05-20', 7),
    ('Add Payment Gateway', '2025-05-25', '2025-06-02', 8),
    ('Update User Manual', '2025-06-05', '2025-06-07', 8),
    ('Conduct Code Review', '2025-06-10', '2025-06-13', 9),
    ('Prepare Marketing Materials', '2025-06-15', '2025-06-18', 9),
    ('Setup CI/CD Pipeline', '2025-06-20', '2025-06-25', 10),
    ('Final Project Review', '2025-06-27', '2025-06-30', 10);


select * from tasks;

Alter sequence task_id_seq RESTART 1;

--address for offices 1-1 relation 


INSERT INTO public.adresses (city, steet, "number", office_id)
VALUES
    ('Gori', 'Stalin Ave', '12F', 6),
    ('Rustavi', 'Tsereteli St', '9G', 7),
    ('Poti', 'Javakhishvili St', '18H', 8),
    ('Akhaltsikhe', 'Tamar Mepe St', '25I', 9),
    ('Mtskheta', 'King Mirian St', '3J', 10);

Delete from adresses;
select  * from adresses; 

Alter sequence adresses_id_seq RESTART 1; 


--which employee works on task info
INSERT INTO public.employee_tasks (employee_id, task_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (13, 1),
    (14, 2),
    (15, 3),
    (16, 4),
    (17, 5),
    (18, 6),
    (19, 7),
    (20, 8);

select * from employee_tasks; 

--data which shows empoyees wrok on project
INSERT INTO public.employees_projects (employee_id, project_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15),
    (16, 16),
    (17, 17),
    (18, 18),
    (19, 19),
    (20, 20),
    (21, 1),
    (22, 2),
    (23, 3),
    (24, 4),
    (25, 5),
    (26, 6),
    (27, 7),
    (28, 8),
    (29, 9),
    (30, 10);

delete from employees_projects;

 select * from employees_projects;  


 --departemnts took room data  
 INSERT INTO public.takerooms (department_id, room_id, take_time)
VALUES
    (1, 1, '2025-11-01 09:00:00'),
    (2, 2, '2025-11-01 10:00:00'),
    (3, 3, '2025-11-01 11:00:00'),
    (4, 4, '2025-11-01 12:00:00'),
    (5, 5, '2025-11-01 13:00:00'),
    (6, 6, '2025-11-01 14:00:00'),
    (7, 7, '2025-11-01 15:00:00'),
    (8, 8, '2025-11-01 16:00:00'),
    (9, 9, '2025-11-01 17:00:00'),
    (10, 10, '2025-11-01 18:00:00');  



--updates; 
UPDATE employees SET email = 'new.mail5@company.com' WHERE id = 5;
UPDATE employees SET surname = 'Mikeladze' WHERE id = 3;
UPDATE salary SET amount = amount * 1.05;
UPDATE projects SET name = 'AI Integration System' WHERE id = 2;
UPDATE projects SET finished = TRUE WHERE id = 1;
UPDATE rooms SET available = FALSE WHERE id = 4;
UPDATE offices SET capacity = capacity + 20 WHERE id = 3;
UPDATE adresses SET steet = 'Freedom Square' WHERE id = 2;
UPDATE salary SET bonus = bonus + 150 WHERE employee_id = 4;
UPDATE tasks SET end_time = end_time + INTERVAL '2 days' WHERE id = 6;   


--joins
select c.id as company_id,d.id as deparment_id, d.name as department_name, 
o.id as office_id,
cl.id as clinet_id, cl.name as client_name,
b.id as budet_id,b.total_amount,
p.id as project_id, p.name as project_name,
t.id as task_id,  t.name  as taks_name,
e.id as employee_id, e.name as employee_name,
a.id as address_id, a.city,
r.id as room_id,
l.id as laptop_id,
s.id as salary_id,s.amount,
tr.department_id as deparmtnet_took_room,
et.employee_id as employees_works_on_task,
ep.employee_id as employees_works_on_projects
from companies c 
join departments d on c.id=d.company_id
join clients cl on c.id=cl.company_id 
join budgets b on c.id=b.company_id 
join offices o on c.id=o.company_id 
join projects p on d.id=p.department_id
join tasks t on  p.id=t.project_id  
join employees  e on d.id=e.department_id 
join addresses a on o.id=a.office_id
join rooms r on o.id=r.office_id
join laptops l on e.id=l.employee_id
join salary s on e.id=s.employee_id
join takerooms tr on d.id=tr.department_id 
join employee_tasks  et on  e.id=et.employee_id 
join employees_projects ep on e.id=ep.employee_id
order by c.id;

--left join
select c.id as company_id , cl.id as client_id from  companies c 
left join clients cl  on  c.id=cl.company_id ; 

--right join
select c.id as company_id , cl.id as client_id from  companies c 
right join clients cl  on  c.id=cl.company_id ;  

--full join
select c.id as company_id , cl.id as client_id from  companies c 
full join clients cl  on  c.id=cl.company_id ; 

select  c.id as company_id , 
d.id as department_id
from  companies c 
full join departments d  on  c.id=d.company_id ;

select  d.id as dep_id , 
e.id as emol_id
from  departments d
right join employees e  on  d.id=e.department_id ; 

select 
e.id as emoloyee_id ,
l.id as laptop_id  
from employees  e left join  laptops l 
on e.id=l.employee_id;

select e.id as employee_id ,
l.id as latpot_id 
from laptops l right  join employees e on  l.employee_id=e.id;

-- group by  with Aggreation functoins and without having
select SUM(age) as sum_age  from employees group by age ; 
select AVG(age) as avarge_age from employees group by age;  
select MIN(age) as  min_age from employees group by age ;  
select MAX(age) as  max_age from employees group by age ; 
select department_id as projcet_id from projects group by department_id order by department_id;
select AVG(age) as avarge_age from clients group by age;
select AVG(age) as avarge_age from clients group by name; 
select MAX(age) as avarge_age from clients group by name; 

select MIN(age) as avarge_age from clients group by id; 
select count(e.id),s.amount
from employees  e join  salary s on e.id=s.employee_id group by s.amount; 























 

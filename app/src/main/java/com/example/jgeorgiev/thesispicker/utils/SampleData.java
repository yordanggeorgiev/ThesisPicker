package com.example.jgeorgiev.thesispicker.utils;

import android.database.sqlite.SQLiteDatabase;

/**
 * Sample data to populate the tables in the db
 * Created by jgeorgiev on 5/24/17.
 */

public class SampleData {
    private SampleData() {
        //empty constructor
    }

    public static void InsertSampleData(SQLiteDatabase db) {
        if (db != null && db.isOpen()) {
            //teachers
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (1,'доц. С. Стоянова','stoyanova@gmail.com','0888987861')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (2,'проф. Р.Кирилов','kirilov@gmail.com','0888987862')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (3,'доц. Моника Цанева','caneva@gmail.com','0888987863')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (4,'доц. Камелия Стефанова','stefanova@gmail.com','0888987864')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (5,'проф. Желязко Желязков','zheliazkov@gmail.com','0888987865')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (6,'доц. Емил Денчев','denchev@gmail.com','0888987866')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (7,'проф. Ваня Лазарова','lazarova@gmail.com','0888987867')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (8,'доц. А. Мурджева','murdzheva@gmail.com','0888987868')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (9,'гл.ас. Митко Радоев','radoev@gmail.com','0888987869')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (10,'гл.ас. Илко Великов','velikov@gmail.com','0888987860')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (11,'доц. С. Петрова','petrova@gmail.com','0888987811')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (12,'проф. Р.Иванова','ivanova@gmail.com','0888987822')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (13,'доц. Росица Димитрова','dimitrova@gmail.com','0888987833')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (14,'доц. Гергана Георгиева','georgieva@gmail.com','0888987844')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (15,'проф. Михаил Михайлов','mihailov@gmail.com','0888987855')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (16,'доц. Емил Александров','aleksandrov@gmail.com','0888987806')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (17,'проф. Ваня Горанова','goranova@gmail.com','0888987877')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (18,'доц. А. Мерджова','merdzhova@gmail.com','0888987888')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (19,'гл.ас. Митко Стоев','stoev@gmail.com','0888987899')");
            db.execSQL("INSERT INTO teachers (teacher_id,teacher_name,email,phone) VALUES (20,'гл.ас. Илия Луков','lukov@gmail.com','0888987800')");

            //theses
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (1,'Информационна система за продажби в магазин за дрехи','Темата е свързана с проектирането и изграждането на компютърни информационни системи за управление продажбите в магазин за дрехи. Темата предполага и изисква познания в областта на продажбите и счетоводството.',10,0)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (2,'Изграждане на Система за управление веригите за доставки','Системата управлява бизнес процеси, свързани с – изпращане на поръчки към доставчик за доставка на артикули (материали и стоки), доставка и склад.',9,1)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (3,'Проектиране и реализация на КИС за разработване на фирмени бизнеспланове','Темата е свързана с проектиране и реализация на компютърни информационни системи за разработка на фирмени бизнеспланове. Темата предполага и изисква задълбочени познания по счетоводство, финанси и закондателството свързано с националните и международни счетоводни стандарти. Подобни системи се използват от фирми, физически лица и финансови консултанти, при подготовката и обосновката на инвестиционни проекти, искания за кредити и др. ',8,0)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (4,'Фактуриране и данъчно отчитане на ДДС','Разработване на компютърна информационна система за издаване и отчетане на фактури, изготвяне на отчети и данъчни ДДС декларации.',7,0)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (5,'Проектиране и реализация на модул Асистент от система за Текущ контрол и формиране на изпитни оценки','Разработка на модул от web приложение, което дава възможност на асистентите да следят посещаемостта на упражнения, да задават  различни видове задания на студенти по отделни дициплини и да регистрират  резултатите  от изпълнението им.',6,1)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (6,'Проектиране и реализация на модул Обмен на данни от система за Текущ контрол и формиране на изпитни оценки', 'Разработка на модул от уеб приложение, който реализира обмен на данни за студенти, оценки от компютърни тестове, протоколи и др. от  университетски бази данни и други програмни продукти',5,0)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (7,'Проектиране и изграждане на БИ приложение за подпомагане взимането на управленски решения чрез репортинг инструменти в конкретен обект.','Анализ на конкретен бизнес проблем, свързан с необходимостта от взимането на управленски решения на базата на многоаспектни и своевременни отчети, за даден обект. Проектиране на концептуален модел за предлаганото БИ решение. Проектиране и описание на стандартни репорти, необходи за подпомагане на управленския процес. Разработване на БИ приложение на QlikView.',4,0)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (8,'Проектиране и изграждане на приложение за БИ решение в конкретен обект.', 'Анализ на конкретен управленски бизнес проблем за даден обект. Проектиране на концептуален модел за предлаганото БИ решение. Приложение на БИ инструмент за реализация.',3,1)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (9,'Изграждане на Система за управление взаимоотношенията с клиенти','Системата управлява бизнес процеси, свързани с – извършване на маркетингови кампании, продажба на артикули (материали и стоки) на клиенти и сервиз (следпродажбено обслужване)',2,0)");
            db.execSQL("INSERT INTO theses (thesis_id,title,details,lead,is_picked) VALUES (10,'Създаване на приложение тип уеб сървиз и клиент за връзка .Net конзола.','Трябва да се изгради уеб сървиз, да се хоства и да се създаде клиент за сървиса, като клиентът трябва да е от тип .Net конзола. Технология и среда за разработка Visual Studio и .Net Framework',1,0)");

            //students
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (1,'Иван Иванов', 121212121, 'КСТ', 51, 0, 9301023441, null, null)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (2,'Георги Георгиев', 121212122, 'КСТ', 52, 1, 9301023442, 2, 2)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (3,'Йордан Йорданов', 121212123, 'КСТ', 53, 1, 9301023443, null, null)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (4,'Стоян Стоянов', 121212124, 'КСТ', 54, 1, 9301023444, null, null)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (5,'Петър Петров', 121212125, 'КСТ', 55, 0, 9301023445, 5, 5)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (6,'Алекснандра Александрова', 121212126, 'КСТ', 56, 1, 9301023446, null, null)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (7,'Петя Петрова', 121212127, 'КСТ', 57, 1, 9301023447, null, null)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (8,'Симеон Симеонов', 121212128, 'КСТ', 58, 0, 9301023448, 8, 8)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (9,'Борислава Борисова', 121212129, 'КСТ', 59, 1, 9301023449, null, null)");
            db.execSQL("INSERT INTO students (student_id,student_name, faculty_number, specialty, administrative_group, is_bachelor, egn, thesis, reviewer) VALUES (10,'Стефан Стефанов', 121212120, 'КСТ', 60, 1, 9301023440, null, null)");
        }
    }
}

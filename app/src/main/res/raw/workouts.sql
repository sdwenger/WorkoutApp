DROP TABLE IF EXISTS DayThemes;
DROP TABLE IF EXISTS Strength;
CREATE TABLE IF NOT EXISTS DayThemes (Day INT, Theme VARCHAR(22));
INSERT INTO DayThemes (Day, Theme) VALUES (1, 'Full Body');
INSERT INTO DayThemes (Day, Theme) VALUES (2, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (3, 'Full Body');
INSERT INTO DayThemes (Day, Theme) VALUES (4, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (5, 'Full Body');
INSERT INTO DayThemes (Day, Theme) VALUES (6, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (7, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (8, 'Upper Body');
INSERT INTO DayThemes (Day, Theme) VALUES (9, 'Lower Body');
INSERT INTO DayThemes (Day, Theme) VALUES (10, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (11, 'Upper Body');
INSERT INTO DayThemes (Day, Theme) VALUES (12, 'Lower Body');
INSERT INTO DayThemes (Day, Theme) VALUES (13, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (14, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (15, 'Push');
INSERT INTO DayThemes (Day, Theme) VALUES (16, 'Pull');
INSERT INTO DayThemes (Day, Theme) VALUES (17, 'Legs');
INSERT INTO DayThemes (Day, Theme) VALUES (18, 'Push');
INSERT INTO DayThemes (Day, Theme) VALUES (19, 'Pull');
INSERT INTO DayThemes (Day, Theme) VALUES (20, 'Legs');
INSERT INTO DayThemes (Day, Theme) VALUES (21, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (22, 'Chest, Triceps, Calves');
INSERT INTO DayThemes (Day, Theme) VALUES (23, 'Legs &amp; Abs');
INSERT INTO DayThemes (Day, Theme) VALUES (24, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (25, 'Shoulders &amp; Calves');
INSERT INTO DayThemes (Day, Theme) VALUES (26, 'Back, Biceps &amp; Abs');
INSERT INTO DayThemes (Day, Theme) VALUES (27, 'Rest');
INSERT INTO DayThemes (Day, Theme) VALUES (28, 'Rest');
CREATE TABLE IF NOT EXISTS Strength (Day INT, Sequence INT, Title VARCHAR(40), Sets INT, RepString VARCHAR(20), Weight INT);
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 1, 'Dumbbell Bench Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 2, 'Lat Pulldown', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 3, 'Overhead Dumbbell Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 4, 'Leg Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 5, 'Lying Leg Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 6, 'Rope Pressdown', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 7, 'Barbell Biceps Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 8, 'Standing Calf Raise', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (1, 9, 'Crunch', 3, '15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 1, 'Dumbbell Bench Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 2, 'Lat Pulldown', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 3, 'Overhead Dumbbell Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 4, 'Leg Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 5, 'Lying Leg Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 6, 'Rope Pressdown', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 7, 'Barbell Biceps Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 8, 'Standing Calf Raise', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (3, 9, 'Crunch', 3, '15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 1, 'Dumbbell Bench Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 2, 'Lat Pulldown', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 3, 'Overhead Dumbbell Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 4, 'Leg Press', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 5, 'Lying Leg Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 6, 'Rope Pressdown', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 7, 'Barbell Biceps Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 8, 'Standing Calf Raise', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (5, 9, 'Crunch', 3, '15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 1, 'Barbell Bench Press', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 2, 'Dumbbell Flye', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 3, 'Barbell Bent-Over Row', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 4, 'Lat Pulldown', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 5, 'Overhead Dumbbell Press', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 6, 'Dumbbell Lateral Raise', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 7, 'Barbell Biceps Curl', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 8, 'Preacher Curl with Cable', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 9, 'Lying EZ-Bar Triceps Extension', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 10, 'Rope Pressdown', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (8, 11, 'Crunch', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (9, 1, 'Leg Press', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (9, 2, 'Leg Extension', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (9, 3, 'Lying Leg Curl', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (9, 4, 'Seated Leg Curl', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (9, 5, 'Standing Calf Raise', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (9, 6, 'Seated Calf Raise', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 1, 'Barbell Bench Press', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 2, 'Dumbbell Flye', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 3, 'Barbell Bent-Over Row', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 4, 'Lat Pulldown', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 5, 'Overhead Dumbbell Press', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 6, 'Dumbbell Lateral Raise', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 7, 'Barbell Biceps Curl', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 8, 'Preacher Curl with Cable', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 9, 'Lying EZ-Bar Triceps Extension', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 10, 'Rope Pressdown', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (11, 11, 'Crunch', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (12, 1, 'Leg Press', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (12, 2, 'Leg Extension', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (12, 3, 'Lying Leg Curl', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (12, 4, 'Seated Leg Curl', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (12, 5, 'Standing Calf Raise', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (12, 6, 'Seated Calf Raise', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (15, 1, 'Incline Barbell Bench Press', 4, '10,10,12,15.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (15, 2, 'Dumbbell Flye', 4, '10,10,12,15.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (15, 3, 'Overhead Dumbbell Press', 4, '10,10,12,15.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (15, 4, 'Smith Machine Upright Row', 4, '8, 8, 10, 12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (15, 5, 'Lying EZ-Bar Triceps Extension', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (15, 6, 'Dumbbell Kickback', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (16, 1, 'Barbell Upright Row', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (16, 2, 'Single-Arm Neutral-Grip Dumbbell Row', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (16, 3, 'Incline Dumbbell Biceps Curl', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (16, 4, 'Preacher Curl with Cable', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (16, 5, 'Reverse Crunch', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (16, 6, 'Crunch', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (17, 1, 'Back Squat', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (17, 2, 'Leg Press', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (17, 3, 'Seated Leg Curl', 4, '8, 8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (17, 4, 'Romanian Deadlift', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (17, 5, 'Standing Calf Raise', 3, '25')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (17, 6, 'Seated Calf Raise', 3, '25')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (18, 1, 'Incline Barbell Bench Press', 4, '10,10,12,15.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (18, 2, 'Dumbbell Flye', 4, '10,10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (18, 3, 'Overhead Dumbbell Press', 4, '10,10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (18, 4, 'Smith Machine Upright Row', 4, '8, 8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (18, 5, 'Lying EZ-Bar Triceps Extension', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (18, 6, 'Dumbbell Kickback', 3, '10,12,15')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (19, 1, 'Barbell Upright Row', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (19, 2, 'Single-Arm Neutral-Grip Dumbbell Row', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (19, 3, 'Incline Dumbbell Biceps Curl', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (19, 4, 'Preacher Curl with Cable', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (19, 5, 'Reverse Crunch', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (19, 6, 'Crunch', 3, '15-20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (20, 1, 'Back Squat', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (20, 2, 'Leg Press', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (20, 3, 'Seated Leg Curl', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (20, 4, 'Romanian Deadlift', 4, '8,8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (20, 5, 'Standing Calf Raise', 3, '25')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (20, 6, 'Seated Calf Raise', 3, '25')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 1, 'Incline Barbell Bench Press', 5, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 2, 'Dumbbell Bench Press', 5, '8,8,10,10,12.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 3, 'Dumbbell Flye', 5, '8,8,10,10,12.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 4, 'Rope Pressdown', 4, '10,10,12,12.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 5, 'Dumbbell Kickback', 3, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 6, 'Lying EZ-Bar Triceps Extension', 3, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 7, 'Standing Calf Raise', 3, '25')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (22, 8, 'Seated Calf Raise', 3, '225')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 1, 'Back Squat', 5, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 2, 'Leg Press', 5, '8,8,10,10,12.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 3, 'Leg Extension', 5, '8, 8,10,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 4, 'Lying Leg Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 5, 'Romanian Deadlift', 3, '8,10,10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 6, 'Seated Leg Curl', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 7, 'Reverse Crunch', 2, '20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (23, 8, 'Crunch', 2, '20')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (25, 1, 'Overhead Dumbbell Press', 4, '12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (25, 2, 'Smith Machine Upright Row', 3, '8,10,12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (25, 3, 'Dumbbell Lateral Raise', 3, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (25, 4, 'Seated Calf Raise', 10, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (26, 1, 'Barbell Bent-Over Row', 5, '12')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (26, 2, 'Lat Pulldown', 5, '8,8,10,12,12.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (26, 3, 'Single-Arm Neutral-Grip Dumbbell Row', 5, '8,8,8,10,10.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (26, 4, 'Barbell Biceps Curl', 4, '10,10,12,12.')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (26, 5, 'Incline Dumbbell Biceps Curl', 3, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (26, 6, 'Preacher Curl with Cable', 3, '10')
INSERT INTO Strength(Day, Sequence, Title, Sets, RepString) VALUES (26, 7, 'Crunch', 3, '20')

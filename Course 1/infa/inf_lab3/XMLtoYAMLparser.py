import re


class Lesson:
    title = None
    time = None
    week = None
    classroom = None
    housing = None
    teacher_name = None
    format = None


def parser(input_file):
    lines = input_file.readlines()

    lessons = []

    for line in lines:
        line = line[:-1].strip()  # delete spaces and \n

        if line in ["<timetable>", "</timetable>"]:
            continue
        elif re.fullmatch(r"\s*<lesson\d>", line):
            lessons.append(Lesson())
        elif "<title>" in line:
            lessons[-1].title = line[7:-8]
        elif "<time>" in line:
            lessons[-1].time = line[6:-7]
        elif "<week>" in line:
            lessons[-1].week = line[6:-7]
        elif "<classroom>" in line:
            lessons[-1].classroom = line[11:-12]
        elif "<housing>" in line:
            lessons[-1].housing = line[9:-10]
        elif "<teacher_name>" in line:
            lessons[-1].teacher_name = line[14:-15]
        elif "<format>" in line:
            lessons[-1].format = line[8:-9]

    return lessons


def to_yaml(lessons):
    output = "timetable:\n"
    for les_num, lesson in enumerate(lessons):
        output += f"  lesson{les_num + 1}:\n"
        output += f"    title: {lesson.title}\n"
        output += f"    time: {lesson.time}\n"
        output += f"    week: {lesson.week}\n"
        output += f"    classroom: {lesson.classroom}\n"
        output += f"    housing: {lesson.housing}\n"
        output += f"    teacher_name: {lesson.teacher_name}\n"
        output += f"    format: {lesson.format}\n"

    return output


in_file = open("timetable.xml", "r", encoding="utf-8")
out_file = open("timetable.yml", "w", encoding="utf-8")
out_file.write(to_yaml(parser(in_file)))
out_file.close()
in_file.close()

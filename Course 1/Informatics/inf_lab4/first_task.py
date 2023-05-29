import re

file = open("filename", "r")  # filename - название файла

string = file.read()
pattern = r"[А-ЯA-Z]\w*\s[А-ЯA-Z]\.[А-ЯA-Z]\."

full_names = re.findall(pattern, string)
second_names = [i.split(" ")[0] for i in full_names]
second_names.sort()

file.close()

print(second_names)

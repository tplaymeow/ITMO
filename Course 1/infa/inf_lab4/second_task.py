import re

file = open("tests/Hamlet.txt", "r")

strings = file.readlines()
pattern = r'\b(?:[bcdfghjklmnpqrstvwxz]*[aeiouy][bcdfghjklmnpqrstvwxz]*){2}\b'

for i in strings:
    if (len(i.strip().split(' ')) == 6) and (len(re.findall(pattern, i.lower())) == 1):
        print(i, end="")

file.close()

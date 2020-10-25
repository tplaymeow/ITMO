import re


def encrypt_nums(x):
    x = int(x.group())
    return str(3 * x * x + 5)


file = open("filename", "r")  # filename - название файла
input_string = file.read()
pattern = r"-?\d+"

output_string = re.sub(pattern, repl=encrypt_nums, string=input_string)

print(output_string)
file.close()

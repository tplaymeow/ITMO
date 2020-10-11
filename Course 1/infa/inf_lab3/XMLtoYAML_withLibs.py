# Версия программы с использованием импортируемых библиотек.
# Была использована библиотека xmlplain
# Библиотека была установленна с помощью: pip install xmlplain


import xmlplain

# Read to plain object
with open("timetable.xml", "r", encoding="utf-8") as in_file:
    root = xmlplain.xml_to_obj(in_file, strip_space=True, fold_dict=True)

# Output plain YAML
with open("timetable_withLib.yml", "w", encoding="utf-8") as out_file:
    xmlplain.obj_to_yaml(root, out_file)

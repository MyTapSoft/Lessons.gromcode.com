Суть работы программы:
1. Файл или хранилище попадают на Controller и проходят первую валидацию на NULL, размеры
файлов и их разрешения (все по условию).
2. Далее файл переводится в Service. Здесь идет проверка на: существует ли файл в БД, есть ли у него хранилище и
исходя из ответа выбирается то или иное действие. (К примеру: если файла нет в БД - он сохраняется. А если есть - просто меняется ID его храилища)
3. Затем обьекты перебрасываются в ДАО. В некоторых ДАО есть транзакции а в некоторых нет. Транзакции существуют там, где
нужно не только сохранить файл, но и добавить пометку в историю об этом в другую таблицу.

Вопросы:
1. Нужна ли проверка на NULL полей обьектов?
2. Есть непонятки где лучше использовать BadRequest а где InternalServer. К примеру: нужно найти файл в БД - но его там нет.
Соответственно из за чего его нет? Пользователь ошибся с ID? Или файла вообще нет но он там должен быть?

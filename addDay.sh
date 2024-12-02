echo $1 > src/input/test/Day$1.txt
echo $1 > src/input/santa/Day$1.txt

cat src/template.kt | sed "s/X/$1/g" | sed "s/template/Day$1/g" > src/Day$1.kt
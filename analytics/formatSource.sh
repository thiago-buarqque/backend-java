files=$(git status --porcelain | cut -b4-)
for file in $files; do
    if [[ $file == *.java ]]; then
        echo "Formatting file '$file'"
        java -jar ./google-java-format-1.15.0-all-deps.jar -r -a "../$file"
    fi
done
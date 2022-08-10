import java.io.File

fun main(args: Array<String>) {

    var oldVersion = "8.3.18.1741"
    var newVersion = "8.3.18.1289"
    if(args.size == 2) {
        oldVersion = args[0]
        newVersion = args[1]
    }
    println("Версия 1С $oldVersion заменена на $newVersion в файлах:")

    val path = File("C:\\inetpub\\wwwroot")

    // Find directories
    for(folder in path.listFiles() ?: return) {
        if (folder.isDirectory) {

            // find config files
            for (configFile in folder.listFiles() ?: continue) {
                replaceVersion1CInFile(configFile, oldVersion, newVersion)
            }
        }
    }

}

fun replaceVersion1CInFile(configFile: File, oldVersion: String, newVersion: String) {

    println(configFile)
    if (!configFile.canWrite()) {
        println("Can't write the file $configFile")
        return
    }
    var textFile = configFile.readText()
    textFile = textFile.replace(oldVersion, newVersion)
    configFile.writeText(textFile)
}
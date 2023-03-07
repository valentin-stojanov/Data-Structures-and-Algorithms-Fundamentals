javac FolderScanner.java
echo Main-Class: FolderScanner > FolderScannerManifest
jar cfm FolderScanner.jar FolderScannerManifest FolderScanner.class
echo java -jar FolderScanner.jar > Start.bat

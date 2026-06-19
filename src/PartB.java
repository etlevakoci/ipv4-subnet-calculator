class PartB {

    public static String calculate(String networkID, String mask) {
        int network = IPUtils.ipToInt(networkID);
        int maskInt = IPUtils.ipToInt(mask);

        int broadcast = network | ~maskInt;
        int firstIP = network + 1;
        int lastIP = broadcast - 1;
        int hosts = lastIP - firstIP + 1;

        return "IP i parë: " + IPUtils.intToIp(firstIP) +
                "\nIP i fundit: " + IPUtils.intToIp(lastIP) +
                "\nNr. Hostesh: " + hosts;
    }
}


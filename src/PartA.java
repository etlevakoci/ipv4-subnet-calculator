class PartA {

    public static String calculate(String ip, String mask) {
        int ipInt = IPUtils.ipToInt(ip);
        int maskInt = IPUtils.ipToInt(mask);

        int network = ipInt & maskInt;
        int broadcast = network | ~maskInt;

        return "Network ID: " + IPUtils.intToIp(network) +
                "\nBroadcast ID: " + IPUtils.intToIp(broadcast);
    }
}


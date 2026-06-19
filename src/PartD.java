class PartD {

    public static String analyzeIP(String ip, String mask) {

        int ipInt = IPUtils.ipToInt(ip);
        int maskInt = IPUtils.ipToInt(mask);

        int network = ipInt & maskInt;
        int broadcast = network | ~maskInt;

        int firstOctet = Integer.parseInt(ip.split("\\.")[0]);
        String ipClass;

        if (firstOctet >= 1 && firstOctet <= 126)
            ipClass = "Class A";
        else if (firstOctet >= 128 && firstOctet <= 191)
            ipClass = "Class B";
        else if (firstOctet >= 192 && firstOctet <= 223)
            ipClass = "Class C";
        else if (firstOctet >= 224 && firstOctet <= 239)
            ipClass = "Class D (Multicast)";
        else
            ipClass = "Class E (Experimental)";

        boolean isPrivate = false;

        if (firstOctet == 10)
            isPrivate = true;
        else if (firstOctet == 172) {
            int secondOctet = Integer.parseInt(ip.split("\\.")[1]);
            if (secondOctet >= 16 && secondOctet <= 31)
                isPrivate = true;
        }
        else if (firstOctet == 192) {
            int secondOctet = Integer.parseInt(ip.split("\\.")[1]);
            if (secondOctet == 168)
                isPrivate = true;
        }

        String ipType = isPrivate ? "Private" : "Public";

        String role;
        if (ipInt == network)
            role = "Network ID";
        else if (ipInt == broadcast)
            role = "Broadcast ID";
        else
            role = "Host i vlefshëm";

        return "IP Address: " + ip +
                "\nKlasa: " + ipClass +
                "\nTipi: " + ipType +
                "\nRoli: " + role;
    }
}


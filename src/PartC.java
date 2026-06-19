class PartC {

    public static String calculate(String networkID, String mask, int subnetsNeeded) {
        int network = IPUtils.ipToInt(networkID);
        int maskInt = IPUtils.ipToInt(mask);

        int totalHostBits = 32 - countMaskBits(maskInt);

        int n = 0;
        while ((1 << n) < subnetsNeeded) n++;

        int hostBitsPerSubnet = totalHostBits - n;
        if (hostBitsPerSubnet < 2) {
            return "Gabim: Nuk ka host të mjaftueshëm për subnetet e kërkuara!";
        }

        int subnetSize = 1 << hostBitsPerSubnet;
        int usableHosts = subnetSize - 2;

        String result = "";
        int currentNetwork = network;
        int totalSubnets = 1 << n;

        for (int i = 1; i <= totalSubnets; i++) {
            int subnetBroadcast = currentNetwork + subnetSize - 1;
            int firstIP = currentNetwork + 1;
            int lastIP = subnetBroadcast - 1;

            result += "Subnet " + i + ":\n";
            result += "Subnet ID: " + IPUtils.intToIp(currentNetwork) + "\n";
            result += "Broadcast ID: " + IPUtils.intToIp(subnetBroadcast) + "\n";
            result += "IP i parë: " + IPUtils.intToIp(firstIP) + "\n";
            result += "IP i fundit: " + IPUtils.intToIp(lastIP) + "\n";
            result += "Nr. Hostesh: " + usableHosts + "\n\n";

            currentNetwork += subnetSize;
        }

        result += "Gjeneruar " + totalSubnets + " subnet për " + subnetsNeeded + " kërkesë\n";
        result += "Subnet size: /" + (32 - hostBitsPerSubnet) + " (" + subnetSize + " IP, " + usableHosts + " usable)\n";

        return result;
    }

    private static int countMaskBits(int mask) {
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if (((mask >> i) & 1) == 1) count++;
            else break;
        }
        return count;
    }
}


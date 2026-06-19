class IPUtils {

    public static int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int result = 0;

        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(parts[i]);
            result = result << 8;
            result = result | octet;
        }
        return result;
    }

    public static String intToIp(int value) {
        int[] octets = new int[4];

        for (int i = 3; i >= 0; i--) {
            octets[i] = value & 255;
            value = value >> 8;
        }

        return octets[0] + "." + octets[1] + "." +
                octets[2] + "." + octets[3];
    }
    public static boolean isValidIP(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) return false;

        for (String part : parts) {
            try {
                int octet = Integer.parseInt(part);
                if (octet < 0 || octet > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    public static boolean isValidSubnetMask(String mask) {
        if (!isValidIP(mask)) return false;

        int maskInt = ipToInt(mask);

        boolean zeroFound = false;

        for (int i = 31; i >= 0; i--) {
            int bit = (maskInt >> i) & 1;

            if (bit == 0)
                zeroFound = true;
            else if (zeroFound)
                return false;
        }
        return true;
    }
    public static boolean isValidNetworkID(String ip, String mask) {
        if (!isValidIP(ip) || !isValidSubnetMask(mask))
            return false;

        int ipInt = ipToInt(ip);
        int maskInt = ipToInt(mask);

        int network = ipInt & maskInt;

        return ipInt == network;
    }


}

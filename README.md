# TCP/IP IPv4 Subnet Calculator

A Java-based IPv4 subnet calculator with a graphical user interface (GUI), built as a Computer Networks course project at the University of Tirana, Faculty of Natural Sciences, Department of Informatics.

---

## Overview

This application allows users to perform IPv4 subnetting calculations through an interactive GUI. It covers everything from basic Network/Broadcast ID computation to full subnet generation and IP role detection — all organized into four functional modules (Part A through D) backed by a shared utility class.

---

## Features

- **Network & Broadcast ID** calculation from an IP address and subnet mask
- **Host range** computation (first IP, last IP, number of valid hosts)
- **Subnet generation** — splits a network into a requested number of subnets, with details for each
- **IP role detection** — identifies the class (A/B/C/D/E), type (private/public), and role (Network ID, Broadcast ID, or valid host) of any given IP
- **Graphical User Interface** built with Java Swing (JFrame + JTabbedPane)
- Input validation for IP addresses, subnet masks, and Network IDs

---

## Project Structure

```
IPv4Calculator/
│
├── IPUtils.java          # Utility class — IP/int conversions and validation
├── PartA.java            # Calculates Network ID and Broadcast ID
├── PartB.java            # Calculates first IP, last IP, and host count
├── PartC.java            # Generates subnets from a base network
├── PartD.java            # Detects IP class, type, and role
└── IPV4CalculatorGUI.java  # Main GUI — connects all parts via tabbed interface
```

---

## Class Descriptions

### `IPUtils`
Helper/utility class used across all modules.

| Method | Description |
|---|---|
| `ipToInt(String ip)` | Converts a dotted-decimal IP string to a 32-bit integer |
| `intToIp(int value)` | Converts a 32-bit integer back to a dotted-decimal IP string |
| `isValidIP(String ip)` | Validates an IP address string |
| `isValidSubnetMask(String mask)` | Validates a subnet mask string |
| `isValidNetworkID(String ip, String mask)` | Checks whether a given IP is a valid Network ID |

---

### `PartA` — Network & Broadcast ID
- **Input:** IP address + Subnet Mask
- **Output:** Network ID, Broadcast ID
- Uses bitwise AND/OR operations on the 32-bit representations.

---

### `PartB` — Host Range
- **Input:** Network ID + Subnet Mask
- **Output:** First valid IP, Last valid IP, Number of valid hosts
- Host count = 2^(host bits) − 2

---

### `PartC` — Subnet Generation
- **Input:** Network ID + Subnet Mask + Number of desired subnets
- **Output (per subnet):**
  - Subnet ID
  - Broadcast ID
  - First and last valid host IP
  - Number of valid hosts
  - Subnet prefix size (e.g. /26, /27)
- Uses bitwise operations and power-of-2 logic to divide the address space.

---

### `PartD` — IP Role Detection
- **Input:** IP address + Subnet Mask
- **Output:**
  - IP class (A, B, C, D, or E)
  - Type: Private or Public
  - Role: Network ID, Broadcast ID, or valid host

---

### `IPV4CalculatorGUI`
Main application window built with Java Swing.

- **Window title:** `TCP/IP IPv4 Subnet Calculator`
- **Layout:** `JTabbedPane` with four tabs:
  - **Tab A** — Network & Broadcast (PartA)
  - **Tab B** — Host Range (PartB)
  - **Tab C** — Subnet Generation (PartC)
  - **Tab D** — IP Role Detection (PartD)
- **Footer:** `Subnetting Project – TCP/IP v4`

---

## IPv4 & Subnetting Background

### IPv4 Addressing
An IPv4 address is a 32-bit number written in dot-decimal notation (e.g. `192.168.1.1`), divided into a **Network ID** (identifies the network) and a **Host ID** (identifies the device within that network). The boundary between these two parts is defined by the subnet mask or CIDR prefix.

### CIDR
CIDR (Classless Inter-Domain Routing) replaces the old class-based system and expresses addresses as `IP/prefix` (e.g. `192.168.1.0/24`). The prefix indicates how many bits belong to the network part.

### Key Subnet Values
| Element | Description |
|---|---|
| Network ID | First address in the subnet (all host bits = 0) |
| Broadcast ID | Last address in the subnet (all host bits = 1) |
| First valid host | Network ID + 1 |
| Last valid host | Broadcast ID − 1 |
| Valid host count | 2^(host bits) − 2 |

---

## Requirements

- Java 8 or higher
- No external libraries required — uses only the Java standard library (`javax.swing`, `java.awt`)

---

## How to Run

```bash
# Compile all classes
javac IPUtils.java PartA.java PartB.java PartC.java PartD.java IPV4CalculatorGUI.java

# Launch the GUI
java IPV4CalculatorGUI
```

---

## Example Usage

**Input (Tab A):**
```
IP Address:   192.168.1.100
Subnet Mask:  255.255.255.0
```

**Output:**
```
Network ID:   192.168.1.0
Broadcast ID: 192.168.1.255
```

---

## Testing

The application was tested both manually and using automated browser-based testing (Katalon Recorder for Chrome/Firefox), verifying results against known subnet calculators including:

- [Subnet Calculator (calculator.net)](https://www.calculator.net/ip-subnet-calculator.html)
- [MXTOOLBOX Subnet Calculator](https://mxtoolbox.com/subnetcalculator.aspx)

---

## Author

**Etleva Koçi**
University of Tirana — Faculty of Natural Sciences, Department of Informatics
Course: Computer Networks
Academic Year: 2026

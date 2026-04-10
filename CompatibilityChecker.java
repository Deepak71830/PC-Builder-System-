public class CompatibilityChecker {

    public static String check(PCBuild build) {

        String warning = "";

        String cpu = build.getCPU().getName();
        String mb = build.getMotherboard().getName();
        String gpu = build.getGPU().getName();
        String psu = build.getPSU().getName();
        String ram = build.getRAM().getName();
        String cooler = build.getCooler().getName();
        String cabinet = build.getCabinet().getName();

        if (cpu.contains("LGA1700") && !mb.contains("LGA1700")) {
            warning += "CPU requires LGA1700 motherboard\n";
        }

        if (cpu.contains("AM4") && !mb.contains("AM4")) {
            warning += "CPU requires AM4 motherboard\n";
        }

        if (cpu.contains("AM5") && !mb.contains("AM5")) {
            warning += "CPU requires AM5 motherboard\n";
        }

        if (gpu.contains("4090") && psu.contains("650W")) {
            warning += "PSU too weak for high-end GPU\n";
        }

        if (ram.contains("DDR5") && cpu.contains("i5-10")) {
            warning += "Old CPU may not support DDR5\n";
        }

        if (cpu.contains("i9") && cooler.contains("Basic")) {
            warning += "Cooling insufficient for high-end CPU\n";
        }

        if (gpu.contains("4090") && cabinet.contains("Mini")) {
            warning += "GPU may not fit in small case\n";
        }

        if (warning.equals("")) {
            return "All components are compatible ✅";
        }

        return warning;
    }
}
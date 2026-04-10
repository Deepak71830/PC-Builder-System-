public class PCBuild {

    Component cpu, gpu, ram, storage, psu, cooler, cabinet, motherboard;

    public void setCPU(Component c) { cpu = c; }
    public void setGPU(Component c) { gpu = c; }
    public void setRAM(Component c) { ram = c; }
    public void setStorage(Component c) { storage = c; }
    public void setPSU(Component c) { psu = c; }
    public void setCooler(Component c) { cooler = c; }
    public void setCabinet(Component c) { cabinet = c; }
    public void setMotherboard(Component c) { motherboard = c; }

    public Component getCPU() { return cpu; }
    public Component getGPU() { return gpu; }
    public Component getRAM() { return ram; }
    public Component getPSU() { return psu; }
    public Component getMotherboard() { return motherboard; }
    public Component getCabinet() { return cabinet; }
    public Component getCooler() { return cooler; }

    public int getTotalPrice() {
        return cpu.getPrice() + gpu.getPrice() + ram.getPrice() +
               storage.getPrice() + psu.getPrice() +
               cooler.getPrice() + cabinet.getPrice() +
               motherboard.getPrice();
    }

    public String getSummary() {
        return "CPU: " + cpu.getName() +
                "\nMotherboard: " + motherboard.getName() +
                "\nGPU: " + gpu.getName() +
                "\nRAM: " + ram.getName() +
                "\nStorage: " + storage.getName() +
                "\nPSU: " + psu.getName() +
                "\nCooler: " + cooler.getName() +
                "\nCase: " + cabinet.getName();
    }
}
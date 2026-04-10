import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUI {

    JFrame frame;

    JComboBox<String> cpuBrand, cpuSeries, cpuModel;
    JComboBox<String> gpuBrand, gpuSeries, gpuModel;
    JComboBox<String> ramType, ramSize;
    JComboBox<String> storageType, storageOption;
    JComboBox<String> psuType, psuPower;
    JComboBox<String> coolerType, coolerOption;
    JComboBox<String> caseType;

    JComboBox<String> motherboardType, motherboardOption;

    JLabel result;

    HashMap<String, String[]> cpuSeriesMap = new HashMap<>();
    HashMap<String, String[]> cpuModelMap = new HashMap<>();
    HashMap<String, String[]> gpuSeriesMap = new HashMap<>();
    HashMap<String, String[]> gpuModelMap = new HashMap<>();
    HashMap<String, String[]> ramMap = new HashMap<>();
    HashMap<String, String[]> storageMap = new HashMap<>();
    HashMap<String, String[]> psuMap = new HashMap<>();
    HashMap<String, String[]> coolerMap = new HashMap<>();
    HashMap<String, String[]> motherboardMap = new HashMap<>();

    public GUI() {

        frame = new JFrame("PC Builder Pro");
        frame.setSize(800, 700);
        frame.setLayout(new BorderLayout());

        initData();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        cpuBrand = new JComboBox<>(new String[]{"Intel", "AMD"});
        cpuSeries = new JComboBox<>();
        cpuModel = new JComboBox<>();

        gpuBrand = new JComboBox<>(new String[]{"NVIDIA", "AMD"});
        gpuSeries = new JComboBox<>();
        gpuModel = new JComboBox<>();

        ramType = new JComboBox<>(new String[]{"DDR4", "DDR5"});
        ramSize = new JComboBox<>();

        storageType = new JComboBox<>(new String[]{"SSD", "HDD"});
        storageOption = new JComboBox<>();

        psuType = new JComboBox<>(new String[]{"Bronze", "Gold"});
        psuPower = new JComboBox<>();

        coolerType = new JComboBox<>(new String[]{"Air", "Liquid"});
        coolerOption = new JComboBox<>();

        motherboardType = new JComboBox<>(new String[]{"Intel", "AMD"});
        motherboardOption = new JComboBox<>();

        caseType = new JComboBox<>(new String[]{
                "Mini Tower - 3000",
                "Mid Tower - 5000",
                "Full Tower - 8000"
        });

        mainPanel.add(createCard("CPU", cpuBrand, cpuSeries, cpuModel));
        mainPanel.add(createCard("Motherboard", motherboardType, motherboardOption));
        mainPanel.add(createCard("GPU", gpuBrand, gpuSeries, gpuModel));
        mainPanel.add(createCard("RAM", ramType, ramSize));
        mainPanel.add(createCard("Storage", storageType, storageOption));
        mainPanel.add(createCard("PSU", psuType, psuPower));
        mainPanel.add(createCard("Cooler", coolerType, coolerOption));
        mainPanel.add(createCard("Case", caseType));

        JButton buildBtn = new JButton("Build PC");
        result = new JLabel("Total: ₹0");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(buildBtn);
        bottomPanel.add(result);

        frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        cpuBrand.addActionListener(e -> update(cpuSeries, cpuSeriesMap.get(cpuBrand.getSelectedItem())));
        cpuSeries.addActionListener(e -> update(cpuModel, cpuModelMap.get(cpuSeries.getSelectedItem())));

        gpuBrand.addActionListener(e -> update(gpuSeries, gpuSeriesMap.get(gpuBrand.getSelectedItem())));
        gpuSeries.addActionListener(e -> update(gpuModel, gpuModelMap.get(gpuSeries.getSelectedItem())));

        ramType.addActionListener(e -> update(ramSize, ramMap.get(ramType.getSelectedItem())));
        storageType.addActionListener(e -> update(storageOption, storageMap.get(storageType.getSelectedItem())));
        psuType.addActionListener(e -> update(psuPower, psuMap.get(psuType.getSelectedItem())));
        coolerType.addActionListener(e -> update(coolerOption, coolerMap.get(coolerType.getSelectedItem())));
        motherboardType.addActionListener(e -> update(motherboardOption, motherboardMap.get(motherboardType.getSelectedItem())));

        buildBtn.addActionListener(e -> build());

        update(cpuSeries, cpuSeriesMap.get("Intel"));
        update(cpuModel, cpuModelMap.get("i5"));
        update(gpuSeries, gpuSeriesMap.get("NVIDIA"));
        update(gpuModel, gpuModelMap.get("RTX 30"));
        update(ramSize, ramMap.get("DDR4"));
        update(storageOption, storageMap.get("SSD"));
        update(psuPower, psuMap.get("Bronze"));
        update(coolerOption, coolerMap.get("Air"));
        update(motherboardOption, motherboardMap.get("Intel"));

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JPanel createCard(String title, JComboBox... boxes) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(boxes.length, 1, 5, 5));
        content.setBackground(Color.WHITE);

        for (JComboBox box : boxes) {
            content.add(box);
        }

        card.add(heading, BorderLayout.NORTH);
        card.add(content, BorderLayout.CENTER);

        return card;
    }

    void update(JComboBox<String> box, String[] data) {
        box.removeAllItems();
        if (data == null) return;
        for (String s : data) box.addItem(s);
    }

    int getPrice(String s) {
        return Integer.parseInt(s.split(" - ")[1]);
    }

    void build() {

        PCBuild build = new PCBuild();

        build.setCPU(new Component(cpuModel.getSelectedItem().toString(), getPrice(cpuModel.getSelectedItem().toString())));
        build.setMotherboard(new Component(motherboardOption.getSelectedItem().toString(), getPrice(motherboardOption.getSelectedItem().toString())));
        build.setGPU(new Component(gpuModel.getSelectedItem().toString(), getPrice(gpuModel.getSelectedItem().toString())));
        build.setRAM(new Component(ramSize.getSelectedItem().toString(), getPrice(ramSize.getSelectedItem().toString())));
        build.setStorage(new Component(storageOption.getSelectedItem().toString(), getPrice(storageOption.getSelectedItem().toString())));
        build.setPSU(new Component(psuPower.getSelectedItem().toString(), getPrice(psuPower.getSelectedItem().toString())));
        build.setCooler(new Component(coolerOption.getSelectedItem().toString(), getPrice(coolerOption.getSelectedItem().toString())));
        build.setCabinet(new Component(caseType.getSelectedItem().toString(), getPrice(caseType.getSelectedItem().toString())));

        int total = build.getTotalPrice();

        result.setText("Total: ₹" + total);

        JOptionPane.showMessageDialog(frame,
                build.getSummary() + "\n\nWarnings:\n" +
                        CompatibilityChecker.check(build));
    }

    void initData() {

    cpuSeriesMap.put("Intel", new String[]{"i3", "i5", "i7", "i9"});
    cpuSeriesMap.put("AMD", new String[]{"Ryzen 3", "Ryzen 5", "Ryzen 7", "Ryzen 9"});

    cpuModelMap.put("i3", new String[]{
            "i3-10100 (LGA1200) - 8000",
            "i3-12100 (LGA1700) - 9000",
            "i3-13100 (LGA1700) - 11000"
    });

    cpuModelMap.put("i5", new String[]{
            "i5-10400 (LGA1200) - 12000",
            "i5-11400 (LGA1200) - 14000",
            "i5-12400 (LGA1700) - 15000",
            "i5-12600K (LGA1700) - 20000",
            "i5-13400 (LGA1700) - 18000",
            "i5-13600K (LGA1700) - 25000"
    });

    cpuModelMap.put("i7", new String[]{
            "i7-10700 (LGA1200) - 22000",
            "i7-11700 (LGA1200) - 26000",
            "i7-12700 (LGA1700) - 28000",
            "i7-13700 (LGA1700) - 32000",
            "i7-13700K (LGA1700) - 35000"
    });

    cpuModelMap.put("i9", new String[]{
            "i9-10900K (LGA1200) - 40000",
            "i9-11900K (LGA1200) - 42000",
            "i9-12900K (LGA1700) - 45000",
            "i9-13900K (LGA1700) - 55000"
    });

    cpuModelMap.put("Ryzen 3", new String[]{
            "R3 3100 (AM4) - 7000",
            "R3 4100 (AM4) - 8500"
    });

    cpuModelMap.put("Ryzen 5", new String[]{
            "R5 3600 (AM4) - 12000",
            "R5 5600 (AM4) - 14000",
            "R5 5600X (AM4) - 16000",
            "R5 7600 (AM5) - 20000"
    });

    cpuModelMap.put("Ryzen 7", new String[]{
            "R7 3700X (AM4) - 20000",
            "R7 5800X (AM4) - 25000",
            "R7 7700X (AM5) - 32000"
    });

    cpuModelMap.put("Ryzen 9", new String[]{
            "R9 3900X (AM4) - 35000",
            "R9 5900X (AM4) - 42000",
            "R9 7900X (AM5) - 50000"
    });

    gpuSeriesMap.put("NVIDIA", new String[]{"GTX", "RTX 20", "RTX 30", "RTX 40"});
    gpuSeriesMap.put("AMD", new String[]{"RX 5000", "RX 6000", "RX 7000"});

    gpuModelMap.put("GTX", new String[]{
            "GTX 1050 - 12000",
            "GTX 1650 - 18000",
            "GTX 1660 - 22000",
            "GTX 1660 Super - 25000"
    });

    gpuModelMap.put("RTX 20", new String[]{
            "RTX 2060 - 28000",
            "RTX 2070 - 35000",
            "RTX 2080 - 45000"
    });

    gpuModelMap.put("RTX 30", new String[]{
            "RTX 3050 - 25000",
            "RTX 3060 - 35000",
            "RTX 3060 Ti - 40000",
            "RTX 3070 - 45000",
            "RTX 3080 - 60000"
    });

    gpuModelMap.put("RTX 40", new String[]{
            "RTX 4060 - 40000",
            "RTX 4060 Ti - 50000",
            "RTX 4070 - 70000",
            "RTX 4080 - 100000",
            "RTX 4090 - 150000"
    });

    gpuModelMap.put("RX 5000", new String[]{
            "RX 5500XT - 18000",
            "RX 5600XT - 22000",
            "RX 5700XT - 30000"
    });

    gpuModelMap.put("RX 6000", new String[]{
            "RX 6600 - 30000",
            "RX 6650XT - 35000",
            "RX 6700XT - 40000",
            "RX 6800 - 50000"
    });

    gpuModelMap.put("RX 7000", new String[]{
            "RX 7600 - 35000",
            "RX 7700XT - 50000",
            "RX 7800XT - 60000",
            "RX 7900XT - 90000"
    });

    ramMap.put("DDR4", new String[]{
            "8GB DDR4 - 3000",
            "16GB DDR4 - 6000",
            "32GB DDR4 - 10000",
            "64GB DDR4 - 18000"
    });

    ramMap.put("DDR5", new String[]{
            "16GB DDR5 - 9000",
            "32GB DDR5 - 15000",
            "64GB DDR5 - 25000"
    });

    storageMap.put("SSD", new String[]{
            "256GB SSD - 2500",
            "512GB SSD - 4000",
            "1TB SSD - 7000",
            "2TB SSD - 12000",
            "NVMe 1TB - 9000",
            "NVMe 2TB - 15000"
    });

    storageMap.put("HDD", new String[]{
            "1TB HDD - 3000",
            "2TB HDD - 5000",
            "4TB HDD - 8000",
            "8TB HDD - 12000"
    });

    psuMap.put("Bronze", new String[]{
            "450W - 3000",
            "550W - 4000",
            "650W - 5000"
    });

    psuMap.put("Gold", new String[]{
            "650W - 6000",
            "750W - 7000",
            "850W - 9000",
            "1000W - 12000"
    });

    coolerMap.put("Air", new String[]{
            "Basic Air - 1500",
            "Hyper 212 - 2500",
            "Deepcool AK620 - 4000",
            "Noctua NH-D15 - 7000"
    });

    coolerMap.put("Liquid", new String[]{
            "120mm - 4000",
            "240mm - 6000",
            "360mm - 9000",
            "Custom Loop - 15000"
    });

    motherboardMap.put("Intel", new String[]{
            "H510 (LGA1200) - 7000",
            "B560 (LGA1200) - 9000",
            "B660 (LGA1700) - 12000",
            "Z690 (LGA1700) - 20000",
            "Z790 (LGA1700) - 25000"
    });

    motherboardMap.put("AMD", new String[]{
            "A520 (AM4) - 6000",
            "B450 (AM4) - 8000",
            "B550 (AM4) - 10000",
            "X570 (AM4) - 18000",
            "B650 (AM5) - 22000",
            "X670 (AM5) - 30000"
    });
}
}
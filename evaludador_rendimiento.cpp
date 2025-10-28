#include <iostream>
#include <vector>
#include <string>
#include <random>
#include <fstream>

// Estructuras
struct CPU {
    std::string nombre;
    int nivel;
    int nucleos;
    float frecuenciaGHz;
};

struct GPU {
    std::string nombre;
    int nivel;
    float vramGB;
    bool rayTracing;
};

struct PC {
    CPU cpu;
    GPU gpu;
    int ram;
    int almacenamiento;
    std::string so;
};

// Listas de componentes
std::vector<CPU> CPUs = {
    {"I3 9100", 1, 4, 3.6f},
    {"I5 9400", 3, 6, 3.9f},
    {"I7 9700", 6, 8, 4.0f},
    {"I9 9900", 9, 8, 5.0f}
};

std::vector<GPU> GPUs = {
    {"GTX 750", 1, 2.0f, false},
    {"GTX 1050", 3, 4.0f, false},
    {"GTX 1650", 5, 4.0f, false},
    {"RTX 2060", 7, 6.0f, true},
    {"RTX 3080", 10, 10.0f, true}
};

std::vector<int> RAMs = {1, 4, 8, 12, 16, 20, 24, 32};
std::vector<int> storages = {128, 256, 512, 1024};
std::vector<std::string> SOs = {"Windows 10", "Windows 11", "Linux"};

// Generador aleatorio moderno
std::mt19937 rng(std::random_device{}());

template <typename T>
T elegirAleatorio(const std::vector<T>& lista) {
    std::uniform_int_distribution<size_t> dist(0, lista.size() - 1);
    return lista[dist(rng)];
}

// Función para generar una PC aleatoria
PC generarPC() {
    PC pc;
    pc.cpu = elegirAleatorio(CPUs);
    pc.gpu = elegirAleatorio(GPUs);
    pc.ram = elegirAleatorio(RAMs);
    pc.almacenamiento = elegirAleatorio(storages);
    pc.so = elegirAleatorio(SOs);
    return pc;
}

// Función para mostrar la PC generada
void mostrarPC(const PC& pc) {
    std::cout << "\nPC Generada:\n";
    std::cout << "CPU: " << pc.cpu.nombre 
              << " | Núcleos: " << pc.cpu.nucleos
              << " | Frecuencia: " << pc.cpu.frecuenciaGHz << " GHz\n";
    std::cout << "GPU: " << pc.gpu.nombre 
              << " | VRAM: " << pc.gpu.vramGB << " GB"
              << " | RayTracing: " << (pc.gpu.rayTracing ? "Sí" : "No") << "\n";
    std::cout << "RAM: " << pc.ram << " GB\n";
    std::cout << "Almacenamiento: " << pc.almacenamiento << " GB\n";
    std::cout << "S.O.: " << pc.so << "\n\n";
}

// Función para guardar la PC en un archivo TXT
void guardarPCenTXT(const PC& pc) {
    std::ofstream archivo("pc_generada.txt");
    if (!archivo) {
        std::cerr << "Error al crear el archivo de salida 'pc_generada.txt'.\n";
        return;
    }

    archivo << "("
            << pc.cpu.nombre << " | Núcleos: " << pc.cpu.nucleos << " | " << pc.cpu.frecuenciaGHz << " GHz, "
            << pc.gpu.nombre << " | VRAM: " << pc.gpu.vramGB << " GB | RayTracing: " << (pc.gpu.rayTracing ? "Sí" : "No") << ", "
            << pc.ram << " GB, "
            << pc.almacenamiento << " GB, "
            << pc.so << ")";

    archivo.close();
    std::cout << "Archivo 'pc_generada.txt' creado exitosamente.\n\n";
}

// Programa principal
int main() {
    std::cout << "===================================\n";
    std::cout << "      GENERADOR DE PC ALEATORIA     \n";
    std::cout << "===================================\n";

    PC pc = generarPC();
    mostrarPC(pc);
    guardarPCenTXT(pc);

    return 0;
}

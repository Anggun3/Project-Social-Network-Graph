# 🌐 Social Network Graph System

> Simulasi jaringan pertemanan berbasis struktur data **Graph** dengan algoritma **BFS & DFS**  
> Mata Kuliah Struktur Data — Informatika, Universitas Muhammadiyah Malang

---

## 📋 Deskripsi

**Social Network Graph System** adalah program simulasi jaringan sosial yang mengimplementasikan struktur data **Graph (Adjacency List)** untuk merepresentasikan hubungan pertemanan antar pengguna. Program ini mampu melakukan pencarian teman bersama, rekomendasi pertemanan, serta menemukan jalur koneksi terpendek antar pengguna menggunakan algoritma BFS.

---

## ✨ Fitur

| No | Fitur | Deskripsi | Algoritma |
|----|-------|-----------|-----------|
| 1 | **Add User** | Menambahkan pengguna baru ke dalam graph | — |
| 2 | **Add Friendship** | Membuat relasi pertemanan antar dua pengguna | — |
| 3 | **Find Mutual Friends** | Mencari teman yang dimiliki bersama oleh dua pengguna | Intersection List |
| 4 | **Friend Recommendation** | Merekomendasikan teman berdasarkan koneksi tingkat 2 | BFS |
| 5 | **Connection Path** | Menemukan jalur koneksi & shortest path antar pengguna | BFS |
| 6 | **Graph Visualization** ⭐ | Menampilkan visualisasi graph dalam bentuk adjacency tree | — |
| 7 | **Display All Users** | Menampilkan seluruh pengguna yang terdaftar | — |

> ⭐ = Fitur Bonus

---

## 🏗️ Struktur Data

```
Graph (Adjacency List)
├── Vertex  → User (ID, Nama)
└── Edge    → Relasi pertemanan (undirected)
```

```
Contoh Adjacency List:
Fira  [U001] ──► Budi, Cika
Budi  [U002] ──► Fira, Dani, Eva
Cika  [U003] ──► Fira, Eva, Fandi
Dani  [U004] ──► Budi, Gita
Eva   [U005] ──► Budi, Cika, Gita
Fandi [U006] ──► Cika
Gita  [U007] ──► Dani, Eva
```

---

## 🗂️ Struktur Project

```
SocialNetworkGraph/
│
├── 📄 Main.java            # Entry point, menu utama, sample data
├── 📄 SocialNetwork.java   # Graph, Adjacency List, semua algoritma
└── 📄 User.java            # Class User (node/vertex)
```

---

## 🖥️ Tampilan Program

```
╔══════════════════════════════════════════════════╗
║         SOCIAL NETWORK GRAPH SYSTEM              ║
║      Struktur Data - Informatika UMM             ║
╚══════════════════════════════════════════════════╝

╔══════════════════════════════════════════════════╗
║                   MENU UTAMA                     ║
╠══════════════════════════════════════════════════╣
║  1. Add User                                     ║
║  2. Add Friendship                               ║
║  3. Find Mutual Friends                          ║
║  4. Friend Recommendation                        ║
║  5. Connection Path + Shortest Path (BFS)        ║
║  6. Graph Visualization          [BONUS]         ║
║  7. Tampilkan Semua User                         ║
║  0. Exit                                         ║
╚══════════════════════════════════════════════════╝
```

---

## 🔍 Contoh Output

**Friend Recommendation (BFS)**
```
Rekomendasi Teman untuk Fira:
-> Dani (ID: U004)  | Jarak: 2
-> Eva  (ID: U005)  | Jarak: 2
-> Fandi(ID: U006)  | Jarak: 2
```

**Connection Path + Shortest Path**
```
Connection Path: Fira --> Gita
-> Path     : Fira -> Budi -> Eva -> Gita
-> Shortest : 3 langkah (Degree of Separation)
```

**Graph Visualization**
```
[U001] Fira (2 teman)
       ├── Budi [U002]
       └── Cika [U003]

[U002] Budi (3 teman)
       ├── Fira [U001]
       ├── Dani [U004]
       └── Eva  [U005]
```

---

## 🧠 Algoritma

### BFS (Breadth-First Search)
Digunakan untuk:
- **Friend Recommendation** — mencari semua user dengan jarak == 2 dari target
- **Connection Path** — menemukan jalur terpendek antar dua user

```
1. Masukkan node awal ke Queue & Visited
2. Selama Queue tidak kosong:
   a. Ambil node dari Queue
   b. Proses setiap tetangga yang belum dikunjungi
   c. Tambahkan ke Queue & catat jarak
3. Rekonstruksi path dari Parent Map
```

### Intersection List
Digunakan untuk **Mutual Friends** — mencari irisan dari dua adjacency list.

---
```
## kelompok 14
SAFIRA            202401370110233
ANGGUN OKTAVIANA  202401370110099
ZALDA SAFRIZA     202401370110088


| **Kelas** | Struktur Data — Informatika UMM |
| **Tahun** | 2025/2026 |

---


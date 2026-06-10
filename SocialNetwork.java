import java.util.*;

public class SocialNetwork {

    private Map<String, List<String>> adjacencyList;
    private Map<String, User> users;

    public SocialNetwork() {
        adjacencyList = new LinkedHashMap<>();
        users = new LinkedHashMap<>();
    }

    // ==================== ADD USER ====================
    public void addUser(String id, String name) {
        if (users.containsKey(id)) {
            System.out.println("  [ERROR] User sudah terdaftar.");
            return;
        }
        users.put(id, new User(id, name));
        adjacencyList.put(id, new ArrayList<>());
        System.out.println("  [SUKSES] User berhasil ditambahkan: " + users.get(id));
    }

    // ==================== ADD FRIENDSHIP ====================
    public void addFriendship(String idA, String idB) {
        if (!users.containsKey(idA) || !users.containsKey(idB)) {
            System.out.println("  [ERROR] User tidak ditemukan.");
            return;
        }
        if (idA.equals(idB)) {
            System.out.println("  [ERROR] Tidak bisa berteman dengan diri sendiri.");
            return;
        }
        if (adjacencyList.get(idA).contains(idB)) {
            System.out.println("  [ERROR] Pertemanan sudah ada.");
            return;
        }
        adjacencyList.get(idA).add(idB);
        adjacencyList.get(idB).add(idA);
        System.out.println("  [SUKSES] Pertemanan terbentuk: "
                + users.get(idA).getName() + " <-> " + users.get(idB).getName());
    }

    // ==================== FIND MUTUAL FRIENDS ====================
    public void findMutualFriends(String idA, String idB) {
        if (!users.containsKey(idA) || !users.containsKey(idB)) {
            System.out.println("  [ERROR] User tidak ditemukan.");
            return;
        }

        // Intersection List
        List<String> intersection = new ArrayList<>(adjacencyList.get(idA));
        intersection.retainAll(adjacencyList.get(idB));

        System.out.println("\n  Mutual Friends antara "
                + users.get(idA).getName() + " dan " + users.get(idB).getName() + ":");
        if (intersection.isEmpty()) {
            System.out.println("  -> Tidak ada mutual friends.");
        } else {
            for (String uid : intersection) {
                System.out.println("  -> " + users.get(uid));
            }
        }
    }

    // ==================== FRIEND RECOMMENDATION (BFS jarak == 2) ====================
    public void friendRecommendations(String targetId) {
        if (!users.containsKey(targetId)) {
            System.out.println("  [ERROR] User tidak ditemukan.");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> jarak = new HashMap<>();

        queue.add(targetId);
        visited.add(targetId);
        jarak.put(targetId, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (String teman : adjacencyList.get(curr)) {
                if (!visited.contains(teman)) {
                    visited.add(teman);
                    jarak.put(teman, jarak.get(curr) + 1);
                    queue.add(teman);
                }
            }
        }

        List<String> rekomendasi = new ArrayList<>();
        for (Map.Entry<String, Integer> e : jarak.entrySet()) {
            if (e.getValue() == 2) rekomendasi.add(e.getKey());
        }

        System.out.println("\n  Rekomendasi Teman untuk " + users.get(targetId).getName() + ":");
        if (rekomendasi.isEmpty()) {
            System.out.println("  -> Tidak ada rekomendasi.");
        } else {
            for (String uid : rekomendasi) {
                System.out.println("  -> " + users.get(uid) + "  | Jarak: " + jarak.get(uid));
            }
        }
    }

    // ==================== CONNECTION PATH + SHORTEST PATH (BFS) ====================
    public void connectionPath(String startId, String endId) {
        if (!users.containsKey(startId) || !users.containsKey(endId)) {
            System.out.println("  [ERROR] User tidak ditemukan.");
            return;
        }
        if (startId.equals(endId)) {
            System.out.println("  [ERROR] User awal dan tujuan sama.");
            return;
        }

        // BFS
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(startId);
        visited.add(startId);
        parent.put(startId, null);
        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(endId)) { found = true; break; }
            for (String neighbor : adjacencyList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, curr);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("\n  Connection Path: "
                + users.get(startId).getName() + " --> " + users.get(endId).getName());
        if (!found) {
            System.out.println("  -> Tidak ada jalur koneksi.");
        } else {
            // Rekonstruksi path
            List<String> path = new ArrayList<>();
            String curr = endId;
            while (curr != null) {
                path.add(0, curr);
                curr = parent.get(curr);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(users.get(path.get(i)).getName());
                if (i < path.size() - 1) sb.append(" -> ");
            }
            System.out.println("  -> Path     : " + sb);
            System.out.println("  -> Shortest : " + (path.size() - 1) + " langkah (Degree of Separation)");
        }
    }

    // ==================== GRAPH VISUALIZATION (BONUS) ====================
    public void graphVisualization() {
        System.out.println("\n  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║            VISUALISASI GRAPH                     ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");

        if (users.isEmpty()) {
            System.out.println("  Graph kosong.");
            return;
        }

        // Hitung total edge (tiap edge dihitung 1x)
        int totalEdge = 0;
        for (List<String> neighbors : adjacencyList.values()) {
            totalEdge += neighbors.size();
        }
        totalEdge /= 2;

        System.out.println("  Total User  : " + users.size());
        System.out.println("  Total Edge  : " + totalEdge);
        System.out.println();

        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            String uid = entry.getKey();
            List<String> friends = entry.getValue();
            String nama = users.get(uid).getName();

            // Node
            System.out.println("  [" + uid + "] " + nama + " (" + friends.size() + " teman)");

            // Edge
            if (friends.isEmpty()) {
                System.out.println("       └── (tidak punya teman)");
            } else {
                for (int i = 0; i < friends.size(); i++) {
                    String prefix = (i == friends.size() - 1) ? "       └──" : "       ├──";
                    System.out.println(prefix + " " + users.get(friends.get(i)).getName()
                            + " [" + friends.get(i) + "]");
                }
            }
            System.out.println();
        }
    }

    // ==================== DISPLAY ALL USERS ====================
    public void displayAllUsers() {
        System.out.println("\n  ===== Daftar Semua User =====");
        if (users.isEmpty()) {
            System.out.println("  Belum ada user.");
        } else {
            for (User u : users.values()) {
                System.out.println("  " + u + " | Teman: " + adjacencyList.get(u.getId()).size());
            }
        }
        System.out.println("  =============================");
    }

    public boolean userExists(String id) { return users.containsKey(id); }
    public Map<String, User> getUsers() { return users; }
    public Map<String, List<String>> getAdjacencyList() { return adjacencyList; }
}

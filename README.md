# Janji
Saya Daffa Faiz Restu Oktavian dengan NIM 2309013 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
# Desain Program
Program ini adalah implementasi sederhana dari game Flappy Bird menggunakan bahasa pemrograman Java dan library Swing untuk antarmuka grafis. Berikut adalah komponen utama dari program:
1. **App**: Kelas utama yang menjalankan program dan menampilkan menu utama.
2. **MainMenu**: Kelas yang menampilkan menu utama dengan tombol untuk memulai permainan.
3. **`FlappyBird**: Kelas utama game yang mengatur logika permainan, seperti pergerakan pemain, pipa, gravitasi, dan deteksi tabrakan.
4. **Player**: Kelas yang merepresentasikan burung (pemain) dalam game.
5. **Pipe**: Kelas yang merepresentasikan pipa-pipa yang menjadi rintangan dalam game.
### Desain Antarmuka
- **Menu Utama**: Menampilkan tombol "Start Game" dan label kredit.
- **Game**: Menampilkan latar belakang, burung, pipa, skor, dan pesan "Game Over" jika permainan selesai.
# Penjelasan Alur Program
1. **Menu Utama**:
   - Program dimulai dengan menampilkan menu utama (`MainMenu`).
   - Pengguna dapat menekan tombol "Start Game" untuk memulai permainan.
2. **Gameplay**:
   - Setelah tombol "Start Game" ditekan, game dimulai dengan menampilkan burung, pipa, dan skor.
   - Burung bergerak ke bawah karena gravitasi, dan pemain dapat menekan tombol `Space` untuk membuat burung melompat.
   - Pipa bergerak dari kanan ke kiri, dan pemain harus menghindari tabrakan dengan pipa.
3. **Game Over**:
   - Jika burung menyentuh pipa atau tanah, permainan berakhir.
   - Pesan "Game Over" ditampilkan, bersama dengan skor akhir dan instruksi untuk memulai ulang permainan dengan menekan tombol `R`.
4. **Restart**:
   - Pemain dapat memulai ulang permainan dengan menekan tombol `R`.
# Cara Menjalankan Program
1. Pastikan Anda memiliki **Java Development Kit (JDK)** terinstal di komputer Anda.
2. Clone repository ini ke komputer Anda.
3. Jalankan file `App.java` untuk memulai program.
4. Nikmati permainan!
# Dokumentasi
<img src = "Dokumentasi/Screenshot 2025-04-12 165004.png">
<img src = "Dokumentasi/Screenshot 2025-04-12 165056.png">
https://github.com/user-attachments/assets/d8af68bd-b5b5-414c-b82d-8b8977faf6ff

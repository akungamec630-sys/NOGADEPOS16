## Pembaruan: mode Quick / Manual (kasir)
- Dua tombol bulat di halaman Kasir: **⚡ Quick** dan **✋ Manual** (pilihan diingat).
- **Quick**: kolom pencarian nama dan kolom Qty disembunyikan. Setiap barcode yang terbaca (kamera, atau scanner/pengetikan barcode + Enter di kolom barcode) langsung masuk keranjang 1 pcs; scan barcode yang sama lagi = qty +1, +1, dst. Baris yang berubah disorot dan info "Nama → qty" muncul; ada peringatan jika melebihi stok.
- **Manual**: seperti biasa (isi qty dulu), kolom pencarian nama/kode tampil, dan tombol scan barcode kamera tetap ada.

## Pembaruan: Struk digital
- Gambar struk (PNG) berisi logo, nama/alamat/telepon toko, no. transaksi, waktu, kasir, daftar item lengkap (qty, harga, diskon, subtotal), total, metode bayar, kembalian, dan teks bawah. Transaksi void diberi stempel VOID.
- Buka lewat tombol **Kirim** di tiap baris Riwayat, tombol **Struk digital** di jendela detail (Owner), atau centang **Kirim struk digital setelah bayar** di Kasir.
- **Bagikan gambar** membuka menu share Android/browser (WhatsApp, Email, Telegram, dll.). Tombol **WhatsApp (teks)** dapat langsung ke nomor pelanggan (08xx otomatis jadi 62xx), **Email (teks)** membuka aplikasi email, **Simpan gambar** menyimpan PNG. Tidak butuh internet untuk membuat struk, dan tidak ada plugin baru, jadi build APK tetap sama.

## Pembaruan: nama toko di header
- Nama toko memakai huruf serif elegan (Georgia/Palatino), lebih besar (27px; 21px di HP), kapital dengan jarak huruf, warna putih ke emas lembut dan bayangan halus. Tinggi header dan letak kolom di bawahnya tidak berubah. Nama yang terlalu panjang dipotong dengan "…".

## Pembaruan: PIN, warna menu, pengaturan struk
- **PIN Owner 4 angka**: masuk mode Owner otomatis begitu 4 angka benar diketik (tanpa tombol Masuk). Jika salah, kolom dikosongkan. Di Pengaturan, PIN wajib tepat 4 angka. (PIN lama yang bukan 4 angka tetap bisa dipakai lewat tombol Masuk sampai diganti.)
- **Menu berwarna**: Kasir (biru), Barang (oranye), Stok (ungu), Riwayat (hijau), Laporan (pink), Pengaturan (abu-biru); kartu tiap halaman punya garis atas dan judul tabel dengan warna menu masing-masing.
- **Pengaturan struk baru**: *Karakter sebelum detail item* (mis. `*` atau `-`, tampil sebelum jumlah, mis. `* 2 KG x 16.000`) dan *Jarak antar produk* (0-3 baris kosong). Berlaku di struk browser maupun printer ESC/POS/RawBT, dan ikut tersinkron antar HP.

## Perbaikan Riwayat Penjualan (terbaru)
- **Semua metode bayar tampil** (Tunai/QRIS/Transfer/Debit): ada filter *Semua metode*, centang *Tampilkan void*, tombol *Semua tanggal*, dan ringkasan total per metode di atas daftar.
- **Tanggal otomatis ikut hari ini**: jika aplikasi dibiarkan terbuka melewati tengah malam, filter tanggal Riwayat/Laporan ikut berganti (sebelumnya tertinggal di tanggal lama sehingga transaksi baru tidak terlihat).
- **Nomor transaksi tidak bisa bentrok lagi**: nomor baru selalu di atas nomor terbesar yang sudah ada (sebelumnya bisa dobel setelah pulihkan backup / pasang ulang / dua HP berkode sama, sehingga satu transaksi menimpa transaksi lain, mis. #100010).
- **Hapus transaksi (khusus Owner)**: tombol *Hapus* di tiap baris Riwayat dan di jendela detail. Stok dikembalikan otomatis (dicatat di Riwayat stok). Transaksi yang sudah di-void tidak mengembalikan stok dua kali. Penghapusan ikut tersinkron ke HP lain.

# POS Toko – paket build

## Perubahan pada HTML (www/index.html)
- Daftar hasil pencarian produk: baris yang AKAN di-input (dipilih panah/Enter) berwarna solid teal + garis kuning di kiri;
  baris yang hanya dilewati kursor mouse berwarna tint lembut. Light & dark mode.
- Penyesuaian layar HP (safe-area, padding). Ekspor CSV/backup memakai Simpan/Bagikan saat berjalan di APK.

## Fitur khusus APK
- Tombol Enter/=> di keyboard HP: ada tombol "✓ Masuk" di samping Qty sebagai cadangan.
- Ikon ⌨️ / 📷 di Kasir untuk memilih ketik atau scan barcode dengan kamera (ML Kit, modul Google diunduh sekali).
- Printer struk: Bluetooth HP lewat aplikasi RawBT (pasangkan printer di Bluetooth HP & di RawBT).

## Perubahan terbaru
- **Perbaikan barcode "tidak dikenal"**: pencarian/scan barcode di Kasir sekarang toleran terhadap spasi/karakter tersembunyi, huruf besar-kecil, barcode tersimpan sebagai angka (bukan teks), nol di depan (UPC-A 12 digit = EAN-13 dengan 0 di depan), apostrof Excel, dan akhiran ".0". Data produk dinormalisasi otomatis saat aplikasi dibuka dan setelah Pulihkan backup / Impor CSV. Barcode/kode yang disimpan lewat Ubah barang juga dibersihkan, dan cek duplikat tidak lagi peka huruf besar-kecil/spasi.
- **Detail transaksi (khusus Owner)**: di Riwayat, klik baris transaksi untuk melihat daftar produk yang terjual (qty, harga, diskon, subtotal, total, bayar, kembali) + tombol Cetak. Di mode Kasir, klik baris tidak melakukan apa-apa.
- **Hapus barang khusus Owner**: di menu Barang, mode Kasir hanya punya tombol Ubah; tombol Hapus hanya muncul di mode Owner.
- **Scan kamera di form barang**: kolom Barcode di Barang → + Barang / Ubah punya tombol 📷. Di APK, kamera otomatis terbuka saat menambah barang baru
  dan angka barcode terbaca langsung masuk ke kolom. (Matikan buka-otomatis dengan `AUTO_SCAN_NEW=false` di `www/index.html`; tombol 📷 tetap ada.)
  Jika barcode sudah dipakai barang lain, muncul peringatan.
- **PPN dihapus**: baris PPN di Kasir, pengaturan PPN (%), serta baris PPN di struk (browser & ESC/POS) sudah dihilangkan. Total = subtotal - diskon.
- **Centang "Cetak struk setelah bayar"** di Kasir (di atas tombol BAYAR). Pilihan diingat antar sesi. Cetak ulang dari Riwayat tetap berfungsi.

## APK (tanpa Android Studio)
1. Upload folder ini ke repo GitHub.
2. Tab Actions → "Build APK" → Run workflow.
3. Unduh `POS-Toko-apk` → `app-debug.apk`, instal di HP.
Lokal: `npm install && npx cap add android && npx cap sync android && cd android && ./gradlew assembleDebug`

## Desktop
- Nativefier (butuh URL hosting): `nativefier/build.bat https://domain-anda/index.html`
- Offline penuh (tanpa hosting): `cd electron-offline && npm install && npm run dist`

## Catatan
- Data tersimpan di IndexedDB per aplikasi: data di APK/desktop TIDAK sama dengan data di browser. Pindahkan via backup/restore.
- Web Serial/WebUSB (scanner serial, printer ESC/POS) tidak tersedia di Android WebView dan di Nativefier.
  Scanner mode keyboard tetap jalan. Cetak struk di desktop: pakai mode "driver Windows".

## Perbaikan bug "registerPlugin is not a function"
- `@capacitor/core` tidak ikut dimuat di halaman web, sehingga `Capacitor.registerPlugin` tidak ada.
- `www/index.html` sekarang memakai `Capacitor.nativePromise` (disediakan native-bridge) lewat fungsi `plug()`.
  `nativeSave()` (backup/ekspor) juga memakai `plug()`. Berlaku untuk RawBT, Filesystem, Share, dan scanner.
- Nama file konfigurasi harus `capacitor.config.json` (titik, bukan garis bawah).

## Lisensi / aktivasi (Mode Owner & Pengaturan)
- Mode Owner (otomatis juga Pengaturan/Laporan) terkunci sampai pembeli memasukkan **Key Registrasi**. Dialog aktivasi menampilkan **Kode Perangkat** (8 karakter, acak, dibuat saat aplikasi pertama dibuka) dan kontak penjual 085252371110 (tombol WhatsApp/Telepon).
- Key dibuat dengan **POS-Keygen** (`keygen.html`, tanda tangan digital Ed25519). Aplikasi hanya memuat kunci PUBLIK (konstanta `PUB` di `www/index.html`), jadi key tidak bisa dipalsukan dari isi APK. Key hanya cocok dengan satu Kode Perangkat dan cukup dimasukkan sekali.
- Menghapus data aplikasi / instal ulang / ganti HP menghasilkan Kode Perangkat baru, sehingga perlu key baru.
- Folder POS-Keygen (berisi `private.key`) JANGAN diunggah ke GitHub. Tidak perlu secret GitHub atau pengaturan build tambahan.
- `LIC_STRICT=true` di `www/index.html` mengunci seluruh aplikasi (bukan hanya Owner) sampai diaktifkan.

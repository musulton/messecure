# Messecure
Aplikasi pengaman pesan singkat, dibuat untuk menghindari terjadinya pencurian informasi dalam layanan SMS,
entah itu ketika proses transmisi data atau ketika pesan sudah tersimpan di SMS Center.

## Spesifikasi
1. Aplikasi ditulis dengan bahasa pemrograman Java.
2. Algoritma yang digunakan adalah kriptografi RSA 512-bit.
3. Pesan yang dapat dikirim terbatas, tidak lebih dari 100 karakter.

## Cara kerja
Aplikasi akan melakukan pengkodean (encode) pesan sebelum dikirim oleh sistem perpesanan pada ponsel. Kemudian pesan yang telah di-kodekan ditransmisikan dan dikirim 
ke Pusat Layanan SMS (SMS Center) operator sebelum akhirnya dikirim ke tujuan/penerima. Pesan tersebut harus diterjemahkan terlebih dahulu sebelum dapat dibaca.

## Langkah-langkah yang harus dipenuhi sebelum mengirim pesan
1. Pengirim harus menentukan 2 kunci terlebih dahulu, diantaranya yaitu kunci public dan private. Kunci private digunakan pengirim untuk pengkodean (encode) pesan sebelum dikirim.
2. Penerima harus mendapatkan kunci public dari pengirim. Kunci public tersebut digunakan untuk menafsirkan kode (decode) pada pesan.

## Pengembangan
Aplikasi dibuat untuk memenuhi kelulusan Tugas Akhir saya di Program Studi Teknik Informatika Universitas Muhammadiyah Sukabumi pada tahun 2018 lalu. 
Aplikasi ini masih dapat dikembangkan karena dibuat dengan sederhana dan adanya kekurangan pada batasan jumlah karakter pesan yang dapat dikirim.
Apabila anda ingin mengembangkan aplikasi ini untuk tugas kuliah ataupun lainya, silahkan fork/download.

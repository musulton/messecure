# Messecure
Aplikasi pengaman pesan singkat, dibuat untuk menghindari terjadinya pencurian informasi dalam layanan SMS,
entah itu ketika proses transmisi data atau ketika pesan sudah tersimpan di SMS Center.

## Halaman antarmuka
| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604" alt="intro" src="https://github.com/musulton/messecure/blob/master/screenshot/intro.jpeg">  Intro |  <img width="1604" alt="dashboard" src="https://github.com/musulton/messecure/blob/master/screenshot/dashboard.jpeg"> Dashboard |<img width="1604" alt="set_key" src="https://github.com/musulton/messecure/blob/master/screenshot/setkey.jpeg"> Setkey|
|<img width="1604" alt="create_message" src="https://github.com/musulton/messecure/blob/master/screenshot/sendmesssage.jpeg"> Create message |  <img width="1604" alt="sentbox" src="https://github.com/musulton/messecure/blob/master/screenshot/sentbox.jpeg"> Sentbox |<img width="1604" alt="inbox" src="https://github.com/musulton/messecure/blob/master/screenshot/inbox.jpeg"> Inbox|
|<img width="1604" alt="conversation" src="https://github.com/musulton/messecure/blob/master/screenshot/boxmessages.jpeg">  |  <img width="1604" alt="instruction" src="https://github.com/musulton/messecure/blob/master/screenshot/instruction.jpeg"> Instruction |<img width="1604" alt="about" src="https://github.com/musulton/messecure/blob/master/screenshot/about.jpeg"> About |

## Spesifikasi
1. Aplikasi ditulis dengan bahasa pemrograman Java.
2. Algoritma yang digunakan adalah kriptografi RSA 512-bit.
3. Pesan yang dapat dikirim terbatas, tidak lebih dari 100 karakter.

## Cara kerja
Aplikasi akan melakukan pengkodean (encode) pesan sebelum dikirim oleh sistem perpesanan pada ponsel. Kemudian pesan yang telah di-kodekan ditransmisikan dan dikirim 
ke Pusat Layanan SMS (SMS Center) operator sebelum akhirnya dikirim ke tujuan/penerima. Pesan tersebut harus diterjemahkan terlebih dahulu sebelum dapat dibaca.

## Langkah-langkah yang harus dipenuhi sebelum mengirim pesan
1. Pengirim dan penerima harus menggunakan aplikasi ini untuk mengirim dan membuka pesan.
2. Pengirim harus menentukan 2 kunci terlebih dahulu, diantaranya yaitu kunci public dan private. Kunci private digunakan pengirim untuk pengkodean (encode) pesan sebelum dikirim.
3. Penerima harus mendapatkan kunci public dari pengirim. Kunci public tersebut digunakan untuk menafsirkan kode (decode) pada pesan.

## Pengembangan
Aplikasi dibuat untuk memenuhi kelulusan Tugas Akhir saya di Program Studi Teknik Informatika Universitas Muhammadiyah Sukabumi pada tahun 2018 lalu. 
Aplikasi ini masih dapat dikembangkan karena dibuat dengan sederhana dan adanya kekurangan pada batasan jumlah karakter pesan yang dapat dikirim.
Apabila anda ingin mengembangkan aplikasi ini untuk tugas kuliah ataupun lainya, silahkan fork/download.

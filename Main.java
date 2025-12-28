import java.util.Scanner;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int chon;

        do {
            System.out.println("\n===== QUAN LY BAN VE MAY BAY =====");
            System.out.println("1. Them chuyen bay");
            System.out.println("2. Them khach hang");
            System.out.println("3. Xem danh sach chuyen bay");
            System.out.println("4. Xem danh sach khach hang");
            System.out.println("5. Ban ve");
            System.out.println("6. Xem danh sach ve da ban");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    themChuyenBay();
                    break;
                case 2:
                    themKhachHang();
                    break;
                case 3:
                    xemChuyenBay();
                    break;
                case 4:
                    xemKhachHang();
                    break;
                case 5:
                    banVe();
                    break;
                case 6:
                    xemVeDaBan();
                    break;
                case 0:
                    System.out.println("Da thoat chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
    }

    // ===== THEM CHUYEN BAY =====
    static void themChuyenBay() {
        try {
            System.out.print("Ma chuyen bay: ");
            String ma = sc.nextLine();
            System.out.print("Noi di: ");
            String di = sc.nextLine();
            System.out.print("Noi den: ");
            String den = sc.nextLine();
            System.out.print("Gia ve: ");
            double gia = Double.parseDouble(sc.nextLine());

            FileWriter fw = new FileWriter("chuyen_bay.txt", true);
            fw.write(ma + ";" + di + ";" + den + ";" + gia + "\n");
            fw.close();

            System.out.println("Them chuyen bay thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi them chuyen bay!");
        }
    }

    // ===== THEM KHACH HANG =====
    static void themKhachHang() {
        try {
            System.out.print("Ma khach hang: ");
            String ma = sc.nextLine();
            System.out.print("Ho ten: ");
            String ten = sc.nextLine();
            System.out.print("So dien thoai: ");
            String sdt = sc.nextLine();

            FileWriter fw = new FileWriter("khach_hang.txt", true);
            fw.write(ma + ";" + ten + ";" + sdt + "\n");
            fw.close();

            System.out.println("Them khach hang thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi them khach hang!");
        }
    }

    // ===== XEM CHUYEN BAY =====
    static void xemChuyenBay() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("chuyen_bay.txt"));
            String line;
            System.out.println("\n--- DANH SACH CHUYEN BAY ---");
            while ((line = br.readLine()) != null) {
                String[] a = line.split(";");
                System.out.println("Ma: " + a[0] + " | " + a[1] + " -> " + a[2] + " | Gia: " + a[3]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Chua co chuyen bay!");
        }
    }

    // ===== XEM KHACH HANG =====
    static void xemKhachHang() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("khach_hang.txt"));
            String line;
            System.out.println("\n--- DANH SACH KHACH HANG ---");
            while ((line = br.readLine()) != null) {
                String[] a = line.split(";");
                System.out.println("Ma: " + a[0] + " | Ten: " + a[1] + " | SDT: " + a[2]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Chua co khach hang!");
        }
    }

    // ===== KIEM TRA MA =====
    static boolean tonTai(String file, String ma) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(ma + ";")) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    static double layGiaVe(String maCB) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("chuyen_bay.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] a = line.split(";");
            if (a[0].equals(maCB)) {
                br.close();
                return Double.parseDouble(a[3]);
            }
        }
        br.close();
        return 0;
    }

    // ===== BAN VE =====
    static void banVe() {
        try {
            System.out.print("Ma khach hang: ");
            String maKH = sc.nextLine();
            if (!tonTai("khach_hang.txt", maKH)) {
                System.out.println("Khach hang KHONG ton tai!");
                return;
            }

            System.out.print("Ma chuyen bay: ");
            String maCB = sc.nextLine();
            if (!tonTai("chuyen_bay.txt", maCB)) {
                System.out.println("Chuyen bay KHONG ton tai!");
                return;
            }

            System.out.print("So luong ve: ");
            int sl = Integer.parseInt(sc.nextLine());

            double gia = layGiaVe(maCB);
            double tongTien = gia * sl;

            FileWriter fw = new FileWriter("ve.txt", true);
            fw.write(maKH + ";" + maCB + ";" + sl + ";" + tongTien + "\n");
            fw.close();

            System.out.println("Ban ve thanh cong!");
            System.out.println("Tong tien: " + tongTien);
        } catch (Exception e) {
            System.out.println("Loi ban ve!");
        }
    }

    // ===== XEM VE DA BAN =====
    static void xemVeDaBan() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("ve.txt"));
            String line;
            System.out.println("\n--- DANH SACH VE DA BAN ---");
            while ((line = br.readLine()) != null) {
                String[] a = line.split(";");
                System.out.println("KH: " + a[0] + " | CB: " + a[1] +
                        " | SL: " + a[2] + " | Tong: " + a[3]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Chua co ve nao!");
        }
    }
}

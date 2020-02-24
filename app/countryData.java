package com.zybooks.countryindex;

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;

public class countryData {

    public static ArrayList<Countries> getList() {
        ArrayList<Countries> countryList = new ArrayList<>();
        countryList.add(new Countries("Aruba",
                R.drawable.aruba,
                "Oranjestad",
                "104,822",
                "Aruban florin",
                new String[]{"66% Aruban", "9.1% Columbian", "4.3% Dutch", "4.1% Dominican", "3.2% Venezuelan"},
                new String[]{"75.3% Roman Catholic", "12% Other", "5.5% None", "4.9% Protestant", "3.4% Other Christian"},
                new String[]{"Sanger yena", "Scabechi", "Giambo", "Sopi mondongo"}
        ));
        countryList.add(new Countries("Afghanistan",
                R.drawable.afghanistan,
                "Kabul",
                "32,225,560",
                "Afghan afghani",
                new String[]{"Pashtun 42%", "Tajik 27%", "Hazara 9%", "Uzbek 9%", "Aimak 4%", "Turkmen 3%", "Baloch 2%", "other 4%"},
                new String[]{"Sunni Muslim 80%", "Shia Muslim 19%", "other 1%"},
                new String[]{""}
        ));
        countryList.add(new Countries("Brazil",
                R.drawable.brazil,
                "Brasilia",
                "210,147,125",
                "Brazilian real",
                new String[]{"white 47.7%", "mulatto (mixed white and black) 43.1%", "black 7.6%", "Asian 1.1%", "indigenous 0.4%"},
                new String[]{"Roman Catholic 64.6%", "other Catholic 0.4%", "Protestant 22.2%" , "other Christian 0.7%", "Spiritist 2.2%", "other 1.4%", "none 8%", "unspecified 0.4%"},
                new String[]{""}
        ));
        countryList.add(new Countries("China",
                R.drawable.china,
                "Beijing",
                "1,403,500,365",
                "Chinese yuan",
                new String[]{"Han Chinese 91.6%", "Zhuang 1.3%", "other 7.1%"},
                new String[]{"Buddhist 18.2%", "Christian 5.1%", "Muslim 1.8%", "folk religion 21.9%", "Hindu < 0.1%", "Jewish < 0.1%", "other 0.7%", "unaffiliated 52.2%"},
                new String[]{""}
        ));
        countryList.add(new Countries("France",
                R.drawable.france,
                "Paris",
                "67,022,000",
                "European euro",
                new String[]{"Celtic and Latin with Teutonic", "Slavic", "North African", "Southeast Asian", "Basque minorities"},
                new String[]{"Christian 63-66%", "Muslim 7-9%", "Buddhist 0.5-0.75%", "Jewish 0.5-0.75%", "other 0.5-1.0%", "none 23-28%"},
                new String[]{""}
        ));
        countryList.add(new Countries("Germany",
                R.drawable.germany,
                "Berlin",
                "83,019,200",
                "European euro",
                new String[]{"German 91.5%", "Turkish 2.4%", "Italian 0.7%", "Greek 0.4%", "Polish 0.4%", "other 4.6%"},
                new String[]{"Protestant 34%", "Roman Catholic 34%", "Islam 4%", "Unaffiliated or other 28%"},
                new String[]{""}
        ));
        countryList.add(new Countries("Italy",
                R.drawable.italy,
                "Rome",
                "60,359,546",
                "European euro",
                new String[]{"Italian (includes small clusters of German-, French-, and Slovene-Italians in the north and Albanian- and Greek-Italians in the south)"},
                new String[]{"Christian 80%", "Atheists and Agnostics 20%"},
                new String[]{""}
        ));
        countryList.add(new Countries("Japan",
                R.drawable.japan,
                "Tokyo",
                "126,317,000",
                "Japanese yen",
                new String[]{"Japanese 98.5%", "Koreans 0.5%", "Chinese 0.4%", "other 0.6%"},
                new String[]{"Shintoism 83.9%", "Buddhism 71.4%", "Christianity 2%", "other 7.8%"},
                new String[]{""}
        ));
        countryList.add(new Countries("Spain",
                R.drawable.spain,
                "Madrid",
                "46,733,038",
                "European euro",
                new String[]{"composite of Mediterranean and Nordic types"},
                new String[]{"Roman Catholic 94%", "other 6%"},
                new String[]{""}
        ));
        countryList.add(new Countries("United States of America",
                R.drawable.america,
                "Washington D.C.",
                "327,167,434",
                "American dollar",
                new String[]{"White: (72.4%)", "Black: (12.6%)", "Asian: (4.8%)", "American Indian and Alaska Native: (0.8%)", "Native Hawaiian and other Pacific Islander: (0.4%)", "Hispanic origin:(16.3%)"},
                new String[]{"Protestant 51.3%"," Roman Catholic 23.9%", "Mormon 1.7%", "other Christian 1.6%", "Jewish 1.7%", "Buddhist 0.7%", "Muslim 0.6%", "other or unspecified 2.5%", "unaffiliated 12.1%", "none 4% "},
                new String[]{""}
        ));
        countryList.add(new Countries("Venezuela",
                R.drawable.venezuela,
                "Caracas",
                "31,568,179",
                "Venezuelan petro",
                new String[]{"Spanish", "Italian", "Portuguese", "Arab", "German", "African", "indigenous people"},
                new String[]{"Roman Catholic 96%", "Protestant 2%", "other 2%"},
                new String[]{""}
        ));
        countryList.add(new Countries("Vietnam",
                R.drawable.vietnam,
                "Hanoi",
                "94,569,072",
                "Vietnamese dong",
                new String[]{"Kinh (Viet) 85.7%", "Tay 1.9%", "Thai 1.8%", "Muong 1.5%", "Khmer 1.5%", "Mong 1.2%", "Nung 1.1%", "Hoa 1%", "other 4.3%"},
                new String[]{"Buddhist 7.9%", "Catholic 6.6%", "Hoa Hao 1.7%", "Cao Dai 0.9%", "Protestant 0.9%", "Muslim 0.1%", "none 81.8%"},
                new String[]{""}
        ));

        return countryList;
    }

    public static Countries getCountry(String name) {
        ArrayList<Countries> countryList = getList();
        for (int i = 0; i < countryList.size(); i++) {
            if(countryList.get(i).getCountryName().equals(name)) {
                return countryList.get(i);
            }
        }
        return null;
    }

}

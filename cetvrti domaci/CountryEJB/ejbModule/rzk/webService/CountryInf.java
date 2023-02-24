package rzk.webService;



import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import country.CountryInfoService;
import country.TCountryInfo;
import country.TCurrency;

/**
 * Session Bean implementation class CountryInf
 */
@Stateless
@WebService
public class CountryInf implements CountryInfRemote {
	
	@WebServiceRef(CountryInfoService.class)
	CountryInfoService info;

    /**
     * Default constructor. 
     */
    public CountryInf() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	@WebMethod
	public String getCode(String code) {

	
		TCountryInfo countryInfo = info.getCountryInfoServiceSoap().fullCountryInfo(code);
		String language = "";
		try {
			language = countryInfo.getLanguages().getTLanguage().get(0).getSName();
		} catch (Exception e) {
			language = "language not found";
		}

		String countryName = info.getCountryInfoServiceSoap().countryName(code);
		String capital = info.getCountryInfoServiceSoap().capitalCity(code);
		TCurrency currencyISO = info.getCountryInfoServiceSoap().countryCurrency(code);
		String currency = currencyISO.getSName();

		String info = "Your country is: " + countryName + ", the capital of " + countryName + " is " + capital
				+ ", the language is " + language + " and the currency is " + currency + " ("
				+ currencyISO.getSISOCode() + ")";

		System.out.println("Info: " + info);
		return info;
	}

}

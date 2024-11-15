package com.email.validator;

public class EmailValidator {

    public boolean hasLocalPart(String email) {
        int index = email.indexOf ("@");
        return index > 0;
    }


    public boolean hasDomainPart(String email) {
            int index = email.indexOf ("@");
            if(index != -1 && index < email.length() - 1) {
                return true;
            } else {
                return false;
            }
    }


    public boolean hasDisallowedSymbols(String email) {
        String disallowedSymbols = "!;,";
        int emailLength = email.length ();
        for (int i = 0; i < emailLength; i++) {
            char c = email.charAt(i);
            if(disallowedSymbols.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    /*
    What the hasIpAddressInDomain() method should do:
    a.Checks if the domain part of the email starts with [.
    b.Checks if the domain part ends with ].
    c.Check if there is a valid IP address between these brackets.
     */

    public boolean hasIpAddressInDomain(String email) {
        boolean hasDomain = hasDomainPart (email);
        if (!hasDomain) {
            return false;
        }
        int index = email.indexOf ("@");
        String domainPart = email.substring (index + 1);
        if (domainPart.startsWith ("[") && domainPart.endsWith ("]")) {
            String ipAddress = domainPart.substring (1, domainPart.length () - 1);
            return isValidIp(ipAddress);
        }
        return isValidDomain (domainPart);
    }


    private boolean isValidDomain(String domain) {
        if(domain.equals ("gmail.com") || domain.equals ("yahoo.com") || domain.equals ("hotmain.com") || domain.equals ("outlook.com")) {
            return true;
        }

        if(domain.endsWith (".ru") || domain.endsWith (".com") || domain.endsWith (".net")) {
            return true;
        }

        return false;
    }


    private boolean isValidIp(String ipAddress) {
        String[] partsOfIp = ipAddress.split ("\\."); //devide ip in 4 parts(split by points)

        if(partsOfIp.length != 4) {
            return false;
        }

        for(String part : partsOfIp) {
            if (part.startsWith("0") && part.length() > 1) {
                return false;
            }
            try {
                int partStringToInt = Integer.parseInt (part);
                if(partStringToInt < 0 || partStringToInt > 255) {
                    return false;
                }
            }catch(NumberFormatException e) {
                throw new IllegalArgumentException ("Invalid argument provided. " + e.getMessage ());
            }
        }
        return true;
    }


    public boolean isEmailValid(String email){
        boolean hasLocalPart = hasLocalPart (email);
        if(!hasLocalPart) {
            return false;
        }


        boolean hasDomainPart = hasDomainPart (email);
        if(!hasDomainPart) {
            return false;
        }

        boolean hasDisallowedSymbols = hasDisallowedSymbols(email);
        if (hasDisallowedSymbols) {
            return false; //
        }


        boolean isLengthValid = isLengthValid(email);
        if (!isLengthValid) {
            return false;
        }

        boolean isLocalPartValid = isLocalPartValid(email);
        if (!isLocalPartValid) {
            return false;
        }

        boolean hasIPAddressInDomain = hasIpAddressInDomain (email);
        if(!hasIPAddressInDomain) {
            return false;
        }


        boolean isDomainPartValid = isDomainPartValid(email);
        if (!isDomainPartValid) {
            return false;
        }
        return true;
    }


    public boolean isLocalPartValid(String email) {
        // extract local part from email
       String localPart = email.substring(0, email.indexOf ("@"));
       if(hasDisallowedSymbols (localPart)) {
           return false;
       }
       return localPart.length () >= 1 && localPart.length () <= 64;
    }


    public boolean isDomainPartValid(String email) {
        //extract domain part from email
        String domainPart = email.substring (email.indexOf ("@") + 1);
        if(hasDisallowedSymbols (domainPart) || domainPart.indexOf (".") == -1) {
            return false;
        }

        if(!hasIpAddressInDomain(email)) {
            return false;
        }
        return true;
    }


    public boolean isLengthValid(String email) {
        int emailLength = email.length ();
        if(emailLength <= 254) {
            return true;
        } else {
            return false;
        }
    }
}

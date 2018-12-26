package somes.files_work.browseFileSystem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by anme on 10.10.16.
 */
public class Some {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> PATH = Arrays.asList(

                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/rd-components/rd-widget/rd-widget.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/rd-components/rd-widget-body/rd-widget-body.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/rd-components/rd-widget-footer/rd-widget-footer.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/rd-components/rd-widget-header/rd-widget-header.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/role/role.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/station/station.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/station-detail/station-detail.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/user/user.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/user-detail/user-detail.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/user-permit-role-organization-assign/user-role-assign.organization.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/user-permit-role-station-assign/user-role-assign.station.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/user-share-role-organization-assign/user-role-share-assign.organization.component.ts",
                        "/home/anme/work/workspaces/hertz/web-authorization/src/app/components/user-share-role-station-assign/user-share-role-assign.station.component.ts"
        );

        PATH.forEach(Some::operateFile);

    }

    private static void operateFile(String path) {
        Pattern patternPath = Pattern.compile("'.*'");

        List<String> linesToOut = new ArrayList<>();

        try (Stream<String> input = Files.lines(Paths.get(path)))
        {

            input.forEach( line -> {
                if(line.contains("moduleId: module.id") || line.contains("moduleId:module.id")) {
                    //nothing
                } else if(line.contains("templateUrl:")) {
                    String s = line.replace("templateUrl:", "template:");
                    Matcher m = patternPath.matcher(s);
                    m.find();
                    String filePath = m.group();
                    filePath = filePath.substring(1, filePath.length()-1);
                    s = s.replace("'"+filePath+"'", "require('./" + filePath+"')");
                    linesToOut.add(s);
                } else if(line.contains("styleUrls:")) {
                    String s = line.replace("styleUrls:", "style:");
                    Matcher m = patternPath.matcher(s);
                    m.find();
                    String filePath = m.group();
                    filePath = filePath.substring(1, filePath.length()-1);
                    s = s.replace("'"+filePath+"'", "require('./" + filePath+"').toString()");
                    linesToOut.add(s);
                } else {
                    linesToOut.add(line);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        //------------------

        try ( PrintWriter output = new PrintWriter(path, "UTF-8"))
        {
            linesToOut.forEach(output::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

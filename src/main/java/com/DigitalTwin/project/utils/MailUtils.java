package com.DigitalTwin.project.utils;


import com.DigitalTwin.project.common.CustomException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class MailUtils {

    private static final String USER = "post7a73@outlook.com"; // 邮箱地址
    private static final String PASSWORD = "zs020219"; // 发件人密码

    /**
     * @param to 收件人邮箱
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(String to, String code) {
        String host = "smtp.office365.com";
        String mailStoreType = "smtp";
        String popPort = "587";
        final Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.store.protocol", mailStoreType);
        props.put("mail.smtp.port", popPort);
        //开启SSL
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", popPort);
        props.put("mail.smtp.socketFactory.fallback", "false");
        try {
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER, PASSWORD);//账号密码
                }
            });
            session.setDebug(false);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            // 设置发件人
            InternetAddress form = new InternetAddress(USER, "数字孪生管廊运维平台", "UTF-8");
            message.setFrom(form);
            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            // 设置邮件标题
            message.setSubject("数字孪生管廊运维平台");
            // 设置邮件的内容体
            message.setContent("<!DOCTYPE html>\n" +
                    "\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <title></title>\n" +
                    "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                    "    <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n" +
                    "    <!--[if mso]>\n" +
                    "    <xml>\n" +
                    "        <o:OfficeDocumentSettings>\n" +
                    "            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                    "            <o:AllowPNG/>\n" +
                    "        </o:OfficeDocumentSettings>\n" +
                    "    </xml><![endif]--><!--[if !mso]><!-->\n" +
                    "    <link href=\"https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@400;700;900&display=swap\" rel=\"stylesheet\"\n" +
                    "          type=\"text/css\"/><!--<![endif]-->\n" +
                    "    <style>\n" +
                    "        * {\n" +
                    "            box-sizing: border-box;\n" +
                    "        }\n" +
                    "\n" +
                    "        body {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        /*.column-1 {\n" +
                    "            background: linear-gradient(white 50%, #777777);\n" +
                    "        }*/\n" +
                    "\n" +
                    "\n" +
                    "        a[x-apple-data-detectors] {\n" +
                    "            color: inherit !important;\n" +
                    "            text-decoration: inherit !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        #MessageViewBody a {\n" +
                    "            color: inherit;\n" +
                    "            text-decoration: none;\n" +
                    "        }\n" +
                    "\n" +
                    "        p {\n" +
                    "            line-height: inherit\n" +
                    "        }\n" +
                    "\n" +
                    "        .desktop_hide,\n" +
                    "        .desktop_hide table {\n" +
                    "            mso-hide: all;\n" +
                    "            display: none;\n" +
                    "            max-height: 0px;\n" +
                    "            overflow: hidden;\n" +
                    "        }\n" +
                    "\n" +
                    "        .image_block img + div {\n" +
                    "            display: none;\n" +
                    "        }\n" +
                    "\n" +
                    "        @media (max-width: 660px) {\n" +
                    "            .desktop_hide table.icons-inner {\n" +
                    "                display: inline-block !important;\n" +
                    "            }\n" +
                    "\n" +
                    "            .icons-inner {\n" +
                    "                text-align: center;\n" +
                    "            }\n" +
                    "\n" +
                    "            .icons-inner td {\n" +
                    "                margin: 0 auto;\n" +
                    "            }\n" +
                    "\n" +
                    "            .image_block img.big,\n" +
                    "            .row-content {\n" +
                    "                width: 100% !important;\n" +
                    "            }\n" +
                    "\n" +
                    "            .mobile_hide {\n" +
                    "                display: none;\n" +
                    "            }\n" +
                    "\n" +
                    "            .stack .column {\n" +
                    "                width: 100%;\n" +
                    "                display: block;\n" +
                    "            }\n" +
                    "\n" +
                    "            .mobile_hide {\n" +
                    "                min-height: 0;\n" +
                    "                max-height: 0;\n" +
                    "                max-width: 0;\n" +
                    "                overflow: hidden;\n" +
                    "                font-size: 0px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .desktop_hide,\n" +
                    "            .desktop_hide table {\n" +
                    "                display: table !important;\n" +
                    "                max-height: none !important;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body style=\"background-color: #f3f2f3; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n" +
                    "       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3f2f3;\" width=\"100%\">\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\"\n" +
                    "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                    "                               role=\"presentation\"\n" +
                    "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\"\n" +
                    "                               width=\"640\">\n" +
                    "                            <tbody>\n" +
                    "                            <tr>\n" +
                    "                                <td class=\"column column-1\"\n" +
                    "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                    "                                    width=\"100%\">\n" +
                    "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                           class=\"divider_block block-1 mobile_hide\" role=\"presentation\"\n" +
                    "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td class=\"pad\">\n" +
                    "                                                <div align=\"center\" class=\"alignment\">\n" +
                    "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                                           role=\"presentation\"\n" +
                    "                                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                    "                                                           width=\"100%\">\n" +
                    "                                                        <tr>\n" +
                    "                                                            <td class=\"divider_inner\"\n" +
                    "                                                                style=\"font-size: 1px; line-height: 1px; border-top: 30px solid #F3F2F3;\">\n" +
                    "                                                                <span></span></td>\n" +
                    "                                                        </tr>\n" +
                    "                                                    </table>\n" +
                    "                                                </div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\"\n" +
                    "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                    "                               role=\"presentation\"\n" +
                    "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3f2f3; color: #000000; width: 640px;\"\n" +
                    "                               width=\"640\">\n" +
                    "                            <tbody>\n" +
                    "                            <tr>\n" +
                    "                                <td class=\"column column-1\"\n" +
                    "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                    "                                    width=\"100%\">\n" +
                    "                                    <div class=\"spacer_block block-1\" style=\"height:1px;line-height:1px;font-size:1px;\">\n" +
                    "                                         \n" +
                    "                                    </div>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\"\n" +
                    "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                    "                               role=\"presentation\"\n" +
                    "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; background-image: url('static/images/bg-shade.jpg'); background-position: center top; background-repeat: repeat; color: #000000; width: 640px;\"\n" +
                    "                               width=\"640\">\n" +
                    "                            <tbody>\n" +
                    "                            <tr>\n" +
                    "                                <td class=\"column column-1\"\n" +
                    "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 60px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                    "                                    width=\"100%\">\n" +
                    "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                           class=\"divider_block block-1 mobile_hide\" role=\"presentation\"\n" +
                    "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td class=\"pad\" style=\"padding-top:50px;\">\n" +
                    "                                                <div align=\"center\" class=\"alignment\">\n" +
                    "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                                           role=\"presentation\"\n" +
                    "                                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                    "                                                           width=\"100%\">\n" +
                    "                                                        <tr>\n" +
                    "                                                            <td class=\"divider_inner\"\n" +
                    "                                                                style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\">\n" +
                    "                                                                <span> </span></td>\n" +
                    "                                                        </tr>\n" +
                    "                                                    </table>\n" +
                    "                                                </div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-2\"\n" +
                    "                                           role=\"presentation\"\n" +
                    "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                    "                                           width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td class=\"pad\">\n" +
                    "                                                <div style=\"font-family: Tahoma, Verdana, sans-serif\">\n" +
                    "                                                    <div class=\"\"\n" +
                    "                                                         style=\"font-size: 12px; font-family: 'Source Sans Pro', Tahoma, Verdana, Segoe, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">\n" +
                    "                                                        <p style=\"margin: 0; font-size: 16px; text-align: center; mso-line-height-alt: 19.2px;\">\n" +
                    "                                                            <span style=\"color:#004afd;font-size:38px;\"><strong>数字孪生管廊运维平台</strong></span>\n" +
                    "                                                        </p>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-3\"\n" +
                    "                                           role=\"presentation\"\n" +
                    "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                    "                                           width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td class=\"pad\"\n" +
                    "                                                style=\"padding-bottom:15px;padding-left:38px;padding-right:38px;padding-top:20px;\">\n" +
                    "                                                <div style=\"font-family: sans-serif\">\n" +
                    "                                                    <div class=\"\"\n" +
                    "                                                         style=\"font-size: 12px; font-family: Helvetica Neue, Helvetica, Arial, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">\n" +
                    "                                                        <p style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;\">\n" +
                    "                                                            <span style=\"font-size:42px;color:#2a272b;\"><strong>" + code + "</strong></span>\n" +
                    "                                                        </p>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-4\"\n" +
                    "                                           role=\"presentation\"\n" +
                    "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                    "                                           width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td class=\"pad\"\n" +
                    "                                                style=\"padding-bottom:10px;padding-left:38px;padding-right:38px;padding-top:10px;\">\n" +
                    "                                                <div style=\"font-family: sans-serif\">\n" +
                    "                                                    <div class=\"\"\n" +
                    "                                                         style=\"font-size: 12px; font-family: Helvetica Neue, Helvetica, Arial, sans-serif; mso-line-height-alt: 18px; color: #777777; line-height: 1.5;\">\n" +
                    "                                                        <p style=\"margin: 0; text-align: center; mso-line-height-alt: 24px;\">\n" +
                    "                                                            <span style=\"font-size:16px;\">您好！欢迎注册数字孪生管廊运维平台！</span>\n" +
                    "                                                        </p>\n" +
                    "                                                        <p style=\"margin: 0; text-align: center; mso-line-height-alt: 24px;\">\n" +
                    "                                                            <span style=\"font-size:16px;\">验证码3分钟内有效</span></p>\n" +
                    "                                                        <p style=\"margin: 0; text-align: center; mso-line-height-alt: 24px;\">\n" +
                    "                                                            <span style=\"font-size:16px;\">请不要把验证码泄露给其他人！</span>\n" +
                    "                                                        </p>\n" +
                    "                                                        <p style=\"margin: 0; text-align: center; mso-line-height-alt: 24px;\">\n" +
                    "                                                            <span style=\"font-size:16px;\">如非本人操作，请忽略</span></p>\n" +
                    "                                                        <p style=\"margin: 0; mso-line-height-alt: 18px;\"> </p>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                    <!--<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-5\"\n" +
                    "                                           role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                    "                                           width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                    "                                                <div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img\n" +
                    "                                                        alt=\"Image\" class=\"big\"\n" +
                    "                                                        src=\"https://i.328888.xyz/2023/05/02/iLKU7o.png\"\n" +
                    "                                                        style=\"display: block; height: auto; border: 0; width: 640px; max-width: 100%;\"\n" +
                    "                                                        title=\"Image\" width=\"640\"/></div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>-->\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\"\n" +
                    "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                    "                               role=\"presentation\"\n" +
                    "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3f2f3; color: #000000; width: 640px;\"\n" +
                    "                               width=\"640\">\n" +
                    "                            <tbody>\n" +
                    "                            <tr>\n" +
                    "                                <td class=\"column column-1\"\n" +
                    "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                    "                                    width=\"100%\">\n" +
                    "                                    <div class=\"spacer_block block-1\" style=\"height:1px;line-height:1px;font-size:1px;\">\n" +
                    "                                         \n" +
                    "                                    </div>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <!--<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\"\n" +
                    "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\"\n" +
                    "                               role=\"presentation\"\n" +
                    "                               style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3f2f3; color: #000000; width: 640px;\"\n" +
                    "                               width=\"640\">\n" +
                    "                            <tbody>\n" +
                    "                            <tr>\n" +
                    "                                <td class=\"column column-1\"\n" +
                    "                                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                    "                                    width=\"100%\">\n" +
                    "                                    <table border=\"0\" cellpadding=\"15\" cellspacing=\"0\"\n" +
                    "                                           class=\"divider_block mobile_hide block-1\" role=\"presentation\"\n" +
                    "                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td class=\"pad\">\n" +
                    "                                                <div align=\"center\" class=\"alignment\">\n" +
                    "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                                           role=\"presentation\"\n" +
                    "                                                           style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                    "                                                           width=\"100%\">\n" +
                    "                                                        <tr>\n" +
                    "                                                            <td class=\"divider_inner\"\n" +
                    "                                                                style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\">\n" +
                    "                                                                <span> </span></td>\n" +
                    "                                                        </tr>\n" +
                    "                                                    </table>\n" +
                    "                                                </div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>-->\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "</table><!-- End -->\n" +
                    "</body>\n" +
                    "</html>", "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            throw new CustomException(e.toString());
        }
    }

}

